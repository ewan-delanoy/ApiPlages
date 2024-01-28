package com.ewan.apiplages.service;



import com.ewan.apiplages.input.*;
import com.ewan.apiplages.output.PreparationFormulaireOutput;
import com.ewan.apiplages.output.ReservationOutput;

import java.util.List;

public interface ApiPlagesService {

    // services utilisés par le client
    public void inscrireNouveauClient(ClientInput clientInput);
    public List<ReservationOutput> reservationsClient (ReservationsViewInput vInput);
    public PreparationFormulaireOutput preparerFormulaire(FormInput fInput);

    public Long effectuerReservation(ReservationInput reservationInput);

    // services utilisés par le concessionnaire

    public void inscrireNouveauConcessionnaire(ConcessionnaireInput concessionnaireInput);

    public List<ReservationOutput> reservationsConcessionnaire (ReservationsViewInput vInput);

    public void accepterReservation (Long reservationId);

    public void refuserReservation (Long reservationId);

    public void supprimerReservation (Long reservationId);



}
