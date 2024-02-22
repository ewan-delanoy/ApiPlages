package com.ewan.apiplages.enumeration;

public enum LoginErrorEnum {
    CONNEXION_OK("Connexion ok"),
    MAUVAIS_MOT_DE_PASSE("Mauvais mot de passe"),
    MAUVAIS_EMAIL("Mauvais email");

    private String nom;

    LoginErrorEnum(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }
    public byte codeErreur() {
        switch (this) {
            case CONNEXION_OK : return  0;
            case MAUVAIS_MOT_DE_PASSE : return 1;
            case MAUVAIS_EMAIL : return 2;
        }
        return 3;
    }
}
