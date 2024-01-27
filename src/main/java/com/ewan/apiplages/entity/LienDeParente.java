package com.ewan.apiplages.entity;

import com.ewan.apiplages.dto.LienDeParenteDto;
import com.ewan.apiplages.enumeration.LienDeParenteEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class LienDeParente {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long lienDeParenteId;

    private String nom;

    private float coefficient;

    public LienDeParente() {
        super();
    }
    public LienDeParente(String nom, float coefficient) {
        this();
        this.nom = nom;
        this.coefficient = coefficient;
    }

    public LienDeParente(LienDeParenteDto lienDeParenteDto) {
        super() ;
        this.lienDeParenteId = lienDeParenteDto.getLienDeParenteId();
        this.nom = lienDeParenteDto.getNom();
        this.coefficient = lienDeParenteDto.getCoefficient();
    }

    public LienDeParente(LienDeParenteEnum lienDeParenteEnum) {
        this(lienDeParenteEnum.getNom(),lienDeParenteEnum.getCoefficient());
    }
    public Long getLienDeParenteId() { return this.lienDeParenteId; }
    public String getNom() { return this.nom; }
    public float getCoefficient() { return this.coefficient; }
}