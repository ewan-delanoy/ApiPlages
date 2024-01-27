package com.ewan.apiplages.dto;


import com.ewan.apiplages.entity.Parasol;
import com.ewan.apiplages.entity.Reservation;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class ReservationDto {
    private Long reservationId;
    private List<ParasolDto> parasols;
    private ClientDto clientDto;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private double montantAReglerEnEuros;
    private String numeroCarte ;
    private byte moisExpiration ;
    private short anneeExpiration ;
    private String cryptogramme ;
    private String remarques;
    private LienDeParenteDto lienDeParenteDto;
    private StatutDto statutDto;

    public ReservationDto(Reservation reservation) {
        List<Parasol> parasols = reservation.getParasols();
        List<ParasolDto> parasolsDto = Arrays.asList();
        for (Parasol p : parasols) {
            parasolsDto.add(new ParasolDto(p));
        }
        this.reservationId = reservation.getReservationId();
        this.parasols = parasolsDto;
        this.clientDto = new ClientDto(reservation.getClient());
        this.dateDebut = reservation.getDateDebut();
        this.dateFin = reservation.getDateFin();
        this.lienDeParenteDto = new LienDeParenteDto(reservation.getLienDeParente());
        this.statutDto = new StatutDto(reservation.getStatut());
    }
}
