package com.ewan.apiplages.input;

public class PaysInput {
    private final String code;
    private final String nom;

    public PaysInput(String code,String nom) {
        this.code = code ;
        this.nom = nom ;
    }
    public String code() { return this.code; }
    public String nom() {return this.nom; }
   

}
