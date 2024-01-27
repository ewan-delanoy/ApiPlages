package com.ewan.apiplages.dto;


import java.time.LocalDate;
import java.util.List;

public class ReservationACreerDto {
    private List<ParasolDto> parasols;
    private ClientDto clientDto;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    // private double montantAReglerEnEuros;
    // private String numeroCarte ;
    // private byte moisExpiration ;
    // private short anneeExpiration ;
    // private String cryptogramme ;
    // private String remarques;
    private LienDeParenteDto lienDeParenteDto;

    public ClientDto getClient() { return this.clientDto; }
    public List<ParasolDto> getParasols() { return this.parasols; }

    public LocalDate getDateDebut() { return this.dateDebut; }

    public LocalDate getDateFin() { return this.dateFin; }
    public LienDeParenteDto getLienDeParente() {return this.lienDeParenteDto;}




}
