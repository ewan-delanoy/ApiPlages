package com.ewan.apiplages.input;

public class PaysInput {
    private final String code;
    private final String nom;

    public PaysInput(String code,String nom) {
        this.code = code ;
        this.nom = nom ;
    }
    public String getCode() { return this.code; }
    public String getNom() {return this.nom; }
   

}
