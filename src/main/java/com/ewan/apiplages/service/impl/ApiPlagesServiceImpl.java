package com.ewan.apiplages.service.impl;

import com.ewan.apiplages.dao.*;
import com.ewan.apiplages.entity.*;
import com.ewan.apiplages.enumeration.StatutEnum;
import com.ewan.apiplages.input.*;
import com.ewan.apiplages.output.*;
import com.ewan.apiplages.service.ApiPlagesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ApiPlagesServiceImpl implements ApiPlagesService {

    private final AffectationDao affectationDao;
    private final ClientDao clientDao;

    private final ConcessionnaireDao concessionnaireDao;

    private final EmplacementDao emplacementDao;
    private final EquipementDao equipementDao;

    private final LienDeParenteDao lienDeParenteDao;
    private final PaysDao paysDao;

    private final PlageDao plageDao;
    private final ReservationDao reservationDao;

    private final StatutDao statutDao;

    public void inscrireNouveauClient(ClientInput clientInput) {
        Client client = new Client(clientInput);
        clientDao.save(client);
    }

    public TripleReservationOutput reservationsClient (Long clientId) {
        return new TripleReservationOutput(
                this.reservationsClientAvecStatut(clientId,StatutEnum.EN_ATTENTE),
                this.reservationsClientAvecStatut(clientId,StatutEnum.ACCEPTEE),
                this.reservationsClientAvecStatut(clientId,StatutEnum.REFUSEE)
        );
    }




    public PreparationFormulaireOutput preparerFormulaire(FormInput fInput) {
        Long plageId = fInput.plageId();
        LocalDate dateDebut = fInput.dateDebut();
        LocalDate dateFin = fInput.dateFin();
        Plage plage = plageDao.getReferenceById(plageId);
        List<Long> idsOccupes = emplacementDao.idsDesEmplacementsOccupes(plage,dateDebut,dateFin);
        List<Emplacement> emplacements = emplacementDao.findByFilePlagePlageId(plageId);
        List<EmplacementOutput> emplacementsDisponibles = new ArrayList<>();

        for (Emplacement emplacement : emplacements) {
            if(!(idsOccupes.contains(emplacement.getEmplacementId()))) {
                emplacementsDisponibles.add(emplacement.toOutput());
            }
        }

        return new PreparationFormulaireOutput(
                emplacementsDisponibles,
                tousLesEquipements(),
                tousLesLiensDeParente(),
                tousLesPays()
        );
    }

    public Long effectuerReservation(ReservationInput reservationInput)
    {
       // Extraire les parametres de l'input
        Long clientId = reservationInput.clientId();
        List<AffectationInput> affectationsInput=reservationInput.affectations();
        LocalDate dateDebut = reservationInput.dateDebut();
        LocalDate dateFin = reservationInput.dateFin();
        String lienDeParenteNom = reservationInput.lienDeParenteNom();

        Statut enAttente = statutDao.findByNom(StatutEnum.EN_ATTENTE.getNom());
        Client client = clientDao.findByUtilisateurId(clientId);
        LienDeParente lienDeParente = lienDeParenteDao.findByNom(lienDeParenteNom);


        Reservation reservation = new Reservation(client,
                dateDebut,dateFin,
                lienDeParente,enAttente);
        reservationDao.save(reservation);

        for (AffectationInput affectationInput : affectationsInput) {
            Emplacement emplacement = emplacementDao.findByEmplacementId(affectationInput.emplacementId());
            Equipement equipement = equipementDao.findByNbDeLitsAndNbDeFauteuils
                    (affectationInput.nbDeLits(),affectationInput.nbDeFauteuils());
            Affectation affectation = new Affectation(
                    emplacement,equipement,reservation
            );
            affectationDao.save(affectation);
        }

        return reservation.getReservationId();
    }


    public void inscrireNouveauConcessionnaire(ConcessionnaireInput concessionnaireInput) {
        Concessionnaire concessionnaire = new Concessionnaire(concessionnaireInput);
        concessionnaireDao.save(concessionnaire);
    }


    public TripleReservationOutput reservationsConcessionnaire (Long concessionnaireId) {
        return new TripleReservationOutput(
                this.reservationsConcessionnaireAvecStatut(concessionnaireId,StatutEnum.EN_ATTENTE),
                this.reservationsConcessionnaireAvecStatut(concessionnaireId,StatutEnum.ACCEPTEE),
                this.reservationsConcessionnaireAvecStatut(concessionnaireId,StatutEnum.REFUSEE)
        );
    }


    public void accepterReservation (Long reservationId) {
        Reservation reservation = reservationDao.findByReservationId(reservationId);
        reservation.setStatut(statutDao.findByNom(StatutEnum.ACCEPTEE.getNom()));
        reservationDao.save(reservation);
    }

    public void refuserReservation (Long reservationId) {
        Reservation reservation = reservationDao.findByReservationId(reservationId);
        reservation.setStatut(statutDao.findByNom(StatutEnum.REFUSEE.getNom()));
        reservationDao.save(reservation);
    }

    public void supprimerReservation (Long reservationId) {
        reservationDao.deleteByReservationId(reservationId);
    }

    private List<ReservationOutput> reservationsClientAvecStatut (Long clientId, StatutEnum statutEnum) {
        List<Object> listeDePaires= reservationDao.affectationsPourClient(clientId,  statutEnum.getNom());
        return this.extraireReservations(listeDePaires);
    }
    private List<ReservationOutput> reservationsConcessionnaireAvecStatut (Long clientId, StatutEnum statutEnum) {
        List<Object> listeDePaires= reservationDao.affectationsPourConcessionnaire(clientId,  statutEnum.getNom());
        return this.extraireReservations(listeDePaires);

    }

    private List<EquipementOutput> tousLesEquipements() {
        List<Equipement> equipements = equipementDao.findAll();
        List<EquipementOutput> equipementsOutput = new ArrayList<>();

        for (Equipement equipement : equipements) {
            equipementsOutput.add(equipement.toOutput());
        }
        return equipementsOutput;
    }

    private List<PaysOutput> tousLesPays() {
        List<Pays> lesPays = paysDao.findAll();
        List<PaysOutput> lesPaysOutput = new ArrayList<>();

        for (Pays pays : lesPays) {
            lesPaysOutput.add(pays.toOutput());
        }
        return lesPaysOutput;
    }

    private List<LienDeParenteOutput> tousLesLiensDeParente() {
        List<LienDeParente> liensDeParente = lienDeParenteDao.findAll();
        List<LienDeParenteOutput> liensDeParenteOutput = new ArrayList<>();

        for (LienDeParente lienDeParente : liensDeParente) {
            liensDeParenteOutput.add(lienDeParente.toOutput());
        }
        return liensDeParenteOutput;
    }

    private List<Affectation> extraireAffectations(Long reservationId,List<Object> listeDePaires) {
        List<Affectation>  affectations = new ArrayList<>();
        for(Object paire: listeDePaires) {
            Object[] paireCastee = (Object[]) paire;
            Reservation premier = (Reservation) paireCastee[0];
            Affectation second = (Affectation) paireCastee[1];
            if(premier.getReservationId().equals(reservationId)) {
                affectations.add(second);
            }
        }
        return affectations;
    }

    private List<ReservationOutput> extraireReservations(List<Object> listeDePaires) {
        List<Reservation>  reservations = new ArrayList<>();
        List<Long> idsDejaTrouves = new ArrayList<>();
        for(Object paire: listeDePaires) {
            Object[] paireCastee = (Object[]) paire;
            Reservation reservation = (Reservation) paireCastee[0];
            Long reservationId = reservation.getReservationId();
            if(!(idsDejaTrouves.contains(reservationId))) {
                reservations.add(reservation);
                idsDejaTrouves.add(reservationId);
            }
        }
        List<ReservationOutput>  reservationsOutput = new ArrayList<>();
        for (Reservation reservation : reservations) {
            List<Affectation> affectations = extraireAffectations(reservation.getReservationId(),listeDePaires);
            reservationsOutput.add(reservation.toOutput(affectations));
        }
        return reservationsOutput;

    }

    public ApiPlagesServiceImpl(AffectationDao affectationDao,
                                ClientDao clientDao,
                                ConcessionnaireDao concessionnaireDao,
                                EmplacementDao emplacementDao,
                                EquipementDao equipementDao,
                                LienDeParenteDao lienDeParenteDao,
                                PaysDao paysDao,
                                PlageDao plageDao,
                                ReservationDao reservationDao,
                                StatutDao statutDao
                                ) {
        this.affectationDao = affectationDao;
        this.clientDao = clientDao ;
        this.concessionnaireDao = concessionnaireDao;
        this.emplacementDao = emplacementDao;
        this.equipementDao = equipementDao;
        this.lienDeParenteDao = lienDeParenteDao;
        this.paysDao = paysDao ;
        this.plageDao = plageDao;
        this.reservationDao = reservationDao;
        this.statutDao = statutDao;
    }
}
