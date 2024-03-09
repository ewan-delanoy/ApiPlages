package com.ewan.apiplages.enumeration;

public enum TypeUtilisateurEnum {
    CLIENT("Client"),
    CONCESSIONNAIRE("Concessionnaire");

    private final String nom;
    TypeUtilisateurEnum(String nom) {
        this.nom = nom;
    }
    public String getNom() {
        return nom;
    }
}
