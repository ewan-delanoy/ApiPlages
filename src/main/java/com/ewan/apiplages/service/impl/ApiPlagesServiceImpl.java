package com.ewan.apiplages.service.impl;

import com.ewan.apiplages.dao.*;
import com.ewan.apiplages.entity.*;
import com.ewan.apiplages.enumeration.LoginErrorEnum;
import com.ewan.apiplages.enumeration.StatutEnum;
import com.ewan.apiplages.input.*;
import com.ewan.apiplages.output.*;
import com.ewan.apiplages.service.ApiPlagesService;
import com.ewan.apiplages.util.Util;
import com.ewan.apiplages.util.crypto.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ApiPlagesServiceImpl implements ApiPlagesService {

    private final AffectationDao affectationDao;
    private final ClientDao clientDao;

    private final EmplacementDao emplacementDao;
    private final EquipementDao equipementDao;

    private final LienDeParenteDao lienDeParenteDao;
    private final PaysDao paysDao;

    private final PlageDao plageDao;
    private final ReservationDao reservationDao;

    private final StatutDao statutDao;

    private final UtilisateurDao utilisateurDao;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    private static final byte NOMBRE_DE_FILES = 8;
    private static final byte NOMBRE_DEMPLACEMENTS_PAR_FILE = 36;

    public Long inscrireNouveauClient(ClientRegistrationInput clientRegistrationInput) {
        Client client = new Client(clientRegistrationInput,encoder);
        clientDao.save(client);
        return client.getUtilisateurId();
    }

    public TripleReservationOutput reservationsClient (Long clientId) {
        List<Object> listeDePaires = reservationDao.reservationsPourClient(clientId);
        List<ReservationOutput> reservations = extraireReservationsOutput(listeDePaires);
        return Util.tripleReservationOutput(reservations);
    }




    public PreparationFormulaireOutput preparerFormulaire(FormInput fInput) {
        Long plageId = fInput.plageId();
        LocalDate dateDebut = fInput.dateDebut();
        LocalDate dateFin = fInput.dateFin();
        Plage plage = plageDao.findByPlageId(plageId);
        List<Long> idsOccupes = emplacementDao.idsDesEmplacementsOccupes(plage,dateDebut,dateFin);
        List<Emplacement> emplacements = emplacementDao.findByFilePlagePlageId(plageId);
        List<ParasolOutput> emplacementsMarques = emplacementsMarques(emplacements,idsOccupes);
        return new PreparationFormulaireOutput(
                emplacementsMarques,
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


    public TripleReservationOutput reservationsConcessionnaire (Long concessionnaireId) {
        List<Object> listeDePaires = reservationDao.reservationsPourConcessionnaire(concessionnaireId);
        List<ReservationOutput> reservations = extraireReservationsOutput(listeDePaires);
        return Util.tripleReservationOutput(reservations);
    }

    public void editerStatutReservation(Long reservationId, String statutNom) {
        Reservation reservation = reservationDao.findByReservationId(reservationId);
        reservation.setStatut(statutDao.findByNom(statutNom));
        reservationDao.save(reservation);
    }


    public void supprimerReservation (Long reservationId) {
        reservationDao.deleteByReservationId(reservationId);
    }

    public UtilisateurOutput getUtilisateurById(Long utilisateurId) {
        return Util.utilisateurOutput(utilisateurDao.findByUtilisateurId(utilisateurId));
    }

    public LoginOutput connecterUtilisateur(LoginInput loginInput) {
        Optional<Utilisateur> utilisateurOptional = utilisateurDao.findByEmail(loginInput.email());
        if(utilisateurOptional.isPresent()) {
            Utilisateur utilisateur = utilisateurOptional.get();


            if (encoder.matches(loginInput.motDePasse(),utilisateur.getMotDePasse())) {
                return Util.loginOutputSucces(utilisateur);
            } else {
                return Util.loginOutputEchec(LoginErrorEnum.MAUVAIS_MOT_DE_PASSE);
            }
        }

        return Util.loginOutputEchec(LoginErrorEnum.MAUVAIS_EMAIL);

    }

    public List<PlageOutput> getPlages() {
        List<Plage> plages = plageDao.findAll();
        List<PlageOutput> plagesOutput = new ArrayList<>();

        for (Plage plage : plages) {
            plagesOutput.add(plage.toOutput());
        }
        return plagesOutput;
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

    private List<ReservationOutput> extraireReservationsOutput(List<Object> listeDePaires) {
        List<Reservation> reservations = extraireReservations(listeDePaires);
        List<ReservationOutput>  reservationsOutput = new ArrayList<>();
        for (Reservation reservation : reservations) {
            List<Affectation> affectations = extraireAffectations(reservation.getReservationId(),listeDePaires);
            reservationsOutput.add(reservation.toOutput(affectations));
        }
        return reservationsOutput;

    }

    private static List<Reservation> extraireReservations(List<Object> listeDePaires) {
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
        return reservations;
    }

    private static List<ParasolOutput>
       emplacementsMarques(List<Emplacement> emplacements,List<Long> idsOccupes) {
        List<ParasolOutput> parasols = new ArrayList<>();
        int nbrEmplacements = ((int)NOMBRE_DE_FILES) * ((int) NOMBRE_DEMPLACEMENTS_PAR_FILE);
        // Premier remplissage, avec des null
        for(int i=1;i <= nbrEmplacements;i++) {
             parasols.add(null);
        }
        // Deuxième et dernier remplissage, avec les vraies valeurs
        for(Emplacement emplacement:emplacements) {
            byte numeroFile = emplacement.getFile().getNumero();
            byte numEmplacement = emplacement.getNumEmplacement();
            Long emplacementId = emplacement.getEmplacementId();
            boolean estDejaPris = idsOccupes.contains(emplacementId);
            parasols.set((numEmplacement-1)+NOMBRE_DEMPLACEMENTS_PAR_FILE*(numeroFile-1),
                    new ParasolOutput(emplacementId, numeroFile,numEmplacement,false,estDejaPris));
        }
        return parasols;
    }

    public ApiPlagesServiceImpl(AffectationDao affectationDao,
                                ClientDao clientDao,
                                EmplacementDao emplacementDao,
                                EquipementDao equipementDao,
                                LienDeParenteDao lienDeParenteDao,
                                PaysDao paysDao,
                                PlageDao plageDao,
                                ReservationDao reservationDao,
                                StatutDao statutDao,
                                UtilisateurDao utilisateurDao
                                ) {
        this.affectationDao = affectationDao;
        this.clientDao = clientDao ;
        this.emplacementDao = emplacementDao;
        this.equipementDao = equipementDao;
        this.lienDeParenteDao = lienDeParenteDao;
        this.paysDao = paysDao ;
        this.plageDao = plageDao;
        this.reservationDao = reservationDao;
        this.statutDao = statutDao;
        this.utilisateurDao = utilisateurDao;
    }
}
