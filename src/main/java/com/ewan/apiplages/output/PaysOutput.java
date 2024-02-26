package com.ewan.apiplages.output;

public class PaysOutput {

    private final String code;
    private final String nom;

    public PaysOutput(String code,String nom) {
        this.code = code ;
        this.nom = nom ;
    }
    public String getCode() { return this.code; }
    public String getNom() {return this.nom; }
}
