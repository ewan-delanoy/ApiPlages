package com.ewan.apiplages.entity;

import com.ewan.apiplages.enumeration.LienDeParenteEnum;
import com.ewan.apiplages.output.LienDeParenteOutput;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


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

    public LienDeParente(LienDeParenteEnum lienDeParenteEnum) {
        this(lienDeParenteEnum.getNom(),lienDeParenteEnum.getCoefficient());
    }

    public LienDeParenteOutput toOutput() {
        return new LienDeParenteOutput(this.nom,this.coefficient);
    }

    public Long getLienDeParenteId() { return this.lienDeParenteId; }
    public String getNom() { return this.nom; }
    public float getCoefficient() { return this.coefficient; }
}
