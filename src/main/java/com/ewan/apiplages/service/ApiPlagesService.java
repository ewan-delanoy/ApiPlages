package com.ewan.apiplages.service;


import com.ewan.apiplages.dto.*;


import java.time.LocalDate;
import java.util.List;

public interface ApiPlagesService {

    // services utilisés par le client
    public ClientDto inscrireNouveauClient(ClientACreerDto clientACreerDto);

    public PreparationFormulaireDto preparerFormulaire(PlageDto plageDto, LocalDate dateDebut, LocalDate dateFin);

    public ReservationDto effectuerReservation(ReservationACreerDto reservationACreerDto);

    // services utilisés par le concessionnaire

    public ConcessionnaireDto inscrireNouveauConcessionnaire(ConcessionnaireACreerDto concessionnaireDto);

    public List<ReservationDto> visualiserReservationsNonTraitees (Long concessionnaireId);

    public void accepterReservation (Long reservationId);

    public void refuserReservation (Long reservationId);



}
