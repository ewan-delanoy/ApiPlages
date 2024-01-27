package com.ewan.apiplages.service.impl;

import com.ewan.apiplages.dao.*;
import com.ewan.apiplages.dto.*;
import com.ewan.apiplages.entity.*;
import com.ewan.apiplages.enumeration.StatutEnum;
import com.ewan.apiplages.input.*;
import com.ewan.apiplages.service.ApiPlagesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ApiPlagesServiceImpl implements ApiPlagesService {

    private final ClientDao clientDao;

    private final ConcessionnaireDao concessionnaireDao;

    private final EquipementDao equipementDao;

    private final LienDeParenteDao lienDeParenteDao;
    private final PaysDao paysDao;

    private final ParasolDao parasolDao;

    private final PlageDao plageDao;
    private final ReservationDao reservationDao;

    private final StatutDao statutDao;

    public void inscrireNouveauClient(ClientInput clientInput) {
        Client client = new Client(clientInput);
        clientDao.save(client);
    }

    public List<ReservationDto> reservationsClient (ReservationsViewInput vInput) {
        List<Reservation> reservations =
                reservationDao.reservationsPourClient(vInput.utilisateurId(), vInput.statutNom());
        List<ReservationDto>  reservationsDto = new ArrayList<>();

        for (Reservation reservation : reservations) {
            reservationsDto.add(new ReservationDto(reservation));
        }
        return reservationsDto;
    }

    public PreparationFormulaireDto preparerFormulaire(FormInput fInput) {
        Long plageId = fInput.plageId();
        LocalDate dateDebut = fInput.dateDebut();
        LocalDate dateFin = fInput.dateFin();
        Plage plage = plageDao.getReferenceById(plageId);
        List<Long> idsOccupes = parasolDao.idsDesParasolsOccupes(plage,dateDebut,dateFin);
        List<Parasol> parasols = parasolDao.findByFilePlagePlageId(plageId);
        List<EmplacementDto> emplacementsDisponibles = new ArrayList<>();

        for (Parasol parasol : parasols) {
            if(!(idsOccupes.contains(parasol.getParasolId()))) {
                emplacementsDisponibles.add(new EmplacementDto(parasol));
            }
        }

        return new PreparationFormulaireDto(
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
        Long plageId = reservationInput.plageId();
        List<SelectionEquipementInput> selections=reservationInput.selections();
        LocalDate dateDebut = reservationInput.dateDebut();
        LocalDate dateFin = reservationInput.dateFin();
        String lienDeParenteNom = reservationInput.lienDeParenteNom();

        Statut enAttente = statutDao.findByNom(StatutEnum.EN_ATTENTE.getNom());
        Client client = clientDao.findByUtilisateurId(clientId);
        LienDeParente lienDeParente = lienDeParenteDao.findByNom(lienDeParenteNom);
        List<Parasol> parasols = new ArrayList<>();
        for (SelectionEquipementInput selection : selections) {
            Parasol parasol = parasolDao.findByParasolId(selection.parasolId());
            Equipement equipement = equipementDao.findByNbDeLitsAndNbDeFauteuils
                    (selection.nbDeLits(),selection.nbDeFauteuils());
            parasol.setEquipement(equipement);
            parasolDao.save(parasol);
            parasols.add(parasol);
        }
        Reservation reservation = new Reservation(client,parasols,
                dateDebut,dateFin,
               lienDeParente,enAttente);
        reservationDao.save(reservation);
        return reservation.getReservationId();
    }


    public void inscrireNouveauConcessionnaire(ConcessionnaireInput concessionnaireInput) {
        Concessionnaire concessionnaire = new Concessionnaire(concessionnaireInput);
        concessionnaireDao.save(concessionnaire);
    }

    public List<ReservationDto> reservationsConcessionnaire (ReservationsViewInput vInput) {
        List<Reservation> reservations =
                reservationDao.reservationsPourConcessionnaire(vInput.utilisateurId(), vInput.statutNom());
        List<ReservationDto>  reservationsDto = new ArrayList<>();

        for (Reservation reservation : reservations) {
            reservationsDto.add(new ReservationDto(reservation));
        }
        return reservationsDto;
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

    private List<EquipementDto> tousLesEquipements() {
        List<Equipement> equipements = equipementDao.findAll();
        List<EquipementDto> equipementsDto = new ArrayList<>();

        for (Equipement equipement : equipements) {
            equipementsDto.add(new EquipementDto(equipement));
        }
        return equipementsDto;
    }

    private List<PaysDto> tousLesPays() {
        List<Pays> lesPays = paysDao.findAll();
        List<PaysDto> lesPaysDto = new ArrayList<>();

        for (Pays pays : lesPays) {
            lesPaysDto.add(new PaysDto(pays));
        }
        return lesPaysDto;
    }

    private List<LienDeParenteDto> tousLesLiensDeParente() {
        List<LienDeParente> liensDeParente = lienDeParenteDao.findAll();
        List<LienDeParenteDto> liensDeParenteDto = new ArrayList<>();

        for (LienDeParente lienDeParente : liensDeParente) {
            liensDeParenteDto.add(new LienDeParenteDto(lienDeParente));
        }
        return liensDeParenteDto;
    }

    public ApiPlagesServiceImpl(ClientDao clientDao,
                                ConcessionnaireDao concessionnaireDao,
                                EquipementDao equipementDao,
                                LienDeParenteDao lienDeParenteDao,
                                PaysDao paysDao,
                                ParasolDao parasolDao,
                                PlageDao plageDao,
                                ReservationDao reservationDao,
                                StatutDao statutDao
                                ) {
        this.clientDao = clientDao ;
        this.concessionnaireDao = concessionnaireDao;
        this.equipementDao = equipementDao;
        this.lienDeParenteDao = lienDeParenteDao;
        this.paysDao = paysDao ;
        this.parasolDao = parasolDao ;
        this.plageDao = plageDao;
        this.reservationDao = reservationDao;
        this.statutDao = statutDao;
    }
}
