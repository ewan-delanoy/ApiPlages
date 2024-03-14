package com.ewan.apiplages.service;



import com.ewan.apiplages.input.*;
import com.ewan.apiplages.output.*;

import java.util.List;


public interface ApiPlagesService {
    // services communs aux clients et aux concessionnaires
    LoginOutput connecterUtilisateur(LoginInput loginInput);

    // services utilisés par le client
    Long inscrireNouveauClient(ClientRegistrationInput clientRegistrationInput);
    TripleReservationOutput reservationsClient (Long utilisateurId);
    PreparationReservationOutput preparerFormulaire(PreparationReservationInput fInput);
    Long effectuerReservation(ReservationInput reservationInput);

    // services utilisés par le concessionnaire
    TripleReservationOutput reservationsConcessionnaire (Long utilisateurId);
    void supprimerReservation (Long reservationId);
    void editerStatutReservation(Long reservationId, String statutNom);
    UtilisateurOutput getUtilisateurById(Long utilisateurId);

    List<PlageOutput> getPlages();

    List<PaysOutput> getPays();

 }
