package com.ewan.apiplages.enumeration;

public enum EquipementEnum {

    UN_LIT("Un lit"),
    DEUX_LITS("Deux lits"),
    UN_LIT_UN_FAUTEUIL("Un lit et un fauteuil"),
    UN_FAUTEUIL("Un fauteuil"),
    DEUX_FAUTEUILS("Deux fauteuils");

    private String nom;

    EquipementEnum(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }
    public byte getNbDeLits() {
        switch (this) {
            case UN_LIT : return 1;
            case DEUX_LITS : return 2;
            case UN_LIT_UN_FAUTEUIL : return 1;
            case UN_FAUTEUIL : return 0;
            case DEUX_FAUTEUILS : return 0;
        }
        return 3;

    }

    public byte getNbDeFauteuils() {
        switch (this) {
            case UN_LIT : return 0;
            case DEUX_LITS : return 0;
            case UN_LIT_UN_FAUTEUIL : return 1;
            case UN_FAUTEUIL : return 1;
            case DEUX_FAUTEUILS : return 2;
        }
        return 3;
    }

}
