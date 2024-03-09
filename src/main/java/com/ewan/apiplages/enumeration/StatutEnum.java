package com.ewan.apiplages.enumeration;

public enum StatutEnum {
    EN_ATTENTE("En attente de traitement"),
    ACCEPTEE("Acceptee"),
    REFUSEE("Refusee");

    private final String nom;
    StatutEnum(String nom) {
        this.nom = nom;
    }
    public String getNom() {
        return nom;
    }

}
