package com.ewan.apiplages.output;

public class LienDeParenteOutput {

    private final String nom;
    private final float coefficient;

    public LienDeParenteOutput(String nom, float coefficient) {
        this.nom = nom;
        this.coefficient = coefficient;
    }

    public String nom() { return this.nom ; }
    public float coefficient() { return this.coefficient ; }
}
