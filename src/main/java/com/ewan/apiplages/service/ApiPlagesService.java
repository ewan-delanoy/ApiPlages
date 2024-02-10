package com.ewan.apiplages.service;



import com.ewan.apiplages.input.*;
import com.ewan.apiplages.output.PreparationFormulaireOutput;
import com.ewan.apiplages.output.TripleReservationOutput;
import com.ewan.apiplages.output.UtilisateurOutput;


public interface ApiPlagesService {

    // services utilisés par le client
    Long inscrireNouveauClient(ClientInput clientInput);
    TripleReservationOutput reservationsClient (Long utilisateurId);
    PreparationFormulaireOutput preparerFormulaire(FormInput fInput);

    Long effectuerReservation(ReservationInput reservationInput);

    // services utilisés par le concessionnaire

    Long inscrireNouveauConcessionnaire(ConcessionnaireInput concessionnaireInput);

    TripleReservationOutput reservationsConcessionnaire (Long utilisateurId);


    void supprimerReservation (Long reservationId);


    void editerStatutReservation(Long reservationId, String statutNom);

    UtilisateurOutput getUtilisateurById(Long utilisateurId);
}
