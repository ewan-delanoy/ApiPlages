package com.ewan.apiplages.entity;

import com.ewan.apiplages.enumeration.LienDeParenteEnum;
import com.ewan.apiplages.output.LienDeParenteOutput;
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

    public LienDeParente(LienDeParenteEnum lienDeParenteEnum) {
        this(lienDeParenteEnum.getNom(),lienDeParenteEnum.getCoefficient());
    }

    public LienDeParenteOutput toOutput() {

        return new LienDeParenteOutput(this.nom,this.coefficient,this.nom.equals(LienDeParenteEnum.AUCUN_LIEN.getNom()));
    }


    public String getNom() { return this.nom; }

    public double getCoefficient() { return this.coefficient; }

}
