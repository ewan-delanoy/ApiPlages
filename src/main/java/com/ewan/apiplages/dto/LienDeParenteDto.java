package com.ewan.apiplages.dto;

import com.ewan.apiplages.entity.LienDeParente;

public class LienDeParenteDto {
    private Long lienDeParenteId;

    private String nom;

    private float coefficient;

    public Long getLienDeParenteId() { return this.lienDeParenteId; }
    public String getNom() { return this.nom; }
    public float getCoefficient() { return this.coefficient; }

    public LienDeParenteDto(LienDeParente lienDeParente) {
        this.lienDeParenteId = lienDeParente.getLienDeParenteId();
        this.nom = lienDeParente.getNom();
        this.coefficient = lienDeParente.getCoefficient();
    }
}
