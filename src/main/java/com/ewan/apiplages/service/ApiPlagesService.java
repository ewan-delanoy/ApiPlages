package com.ewan.apiplages.service;


import com.ewan.apiplages.dto.*;
import com.ewan.apiplages.entity.LienDeParente;
import com.ewan.apiplages.input.*;


import java.time.LocalDate;
import java.util.List;

public interface ApiPlagesService {

    // services utilisés par le client
    public void inscrireNouveauClient(ClientInput clientInput);
    public List<ReservationDto> reservationsClient (ReservationsViewInput vInput);
    public PreparationFormulaireDto preparerFormulaire(FormInput fInput);

    public Long effectuerReservation(ReservationInput reservationInput);

    // services utilisés par le concessionnaire

    public void inscrireNouveauConcessionnaire(ConcessionnaireInput concessionnaireInput);

    public List<ReservationDto> reservationsConcessionnaire (ReservationsViewInput vInput);

    public void accepterReservation (Long reservationId);

    public void refuserReservation (Long reservationId);

    public void supprimerReservation (Long reservationId);



}
