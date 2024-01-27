package com.ewan.apiplages.service;


import com.ewan.apiplages.dto.*;
import com.ewan.apiplages.entity.LienDeParente;
import com.ewan.apiplages.input.ReservationInput;


import java.time.LocalDate;
import java.util.List;

public interface ApiPlagesService {

    // services utilisés par le client
    public ClientDto inscrireNouveauClient(ClientACreerDto clientACreerDto);
    public List<ReservationDto> mesReservations (Long clientId,String statutNom);
    public PreparationFormulaireDto preparerFormulaire(Long plageId, LocalDate dateDebut, LocalDate dateFin);

    public Long effectuerReservation(ReservationInput reservationInput);

    // services utilisés par le concessionnaire

    public ConcessionnaireDto inscrireNouveauConcessionnaire(ConcessionnaireACreerDto concessionnaireDto);

    public List<ReservationDto> visualiserReservationsNonTraitees (Long concessionnaireId);

    public void accepterReservation (Long reservationId);

    public void refuserReservation (Long reservationId);

    public void supprimerReservation (Long reservationId);



}
