package com.ewan.apiplages.enumeration;

public enum LienDeParenteEnum {
    FRERE_SOEUR("Frère/Soeur"),
    COUSIN_E("Cousin/Cousine"),
    AUCUN_LIEN("Aucun lien");

    private String nom;

    LienDeParenteEnum(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }
    public float getCoefficient() {
        switch (this) {
            case FRERE_SOEUR : return 0.5f;
            case COUSIN_E : return 0.75f;
            case AUCUN_LIEN : return 1f;
        }
        return 0f;
    }

}
