package com.ewan.apiplages.input;

import java.time.LocalDate;

public class FormInput {
    
    private final Long plageId;
    private final LocalDate dateDebut;
    private final LocalDate dateFin;

    public FormInput(Long plageId, LocalDate dateDebut, LocalDate dateFin) {
        this.plageId = plageId;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public Long getPlageId() { return this.plageId ; }
    public LocalDate getDateDebut() { return this.dateDebut ; }
    public LocalDate getDateFin() { return this.dateFin ; }

}
