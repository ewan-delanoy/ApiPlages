package com.ewan.apiplages.enumeration;

public enum LoginErrorEnum {
    CONNEXION_OK("Connexion ok"),
    MAUVAIS_MOT_DE_PASSE("Mauvais mot de passe"),
    MAUVAIS_EMAIL("Mauvais email");

    private final String nom;

    LoginErrorEnum(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }
    public byte codeErreur() {
        return switch (this) {
            case CONNEXION_OK -> 0;
            case MAUVAIS_MOT_DE_PASSE -> 1;
            case MAUVAIS_EMAIL -> 2;
        };
    }
}
