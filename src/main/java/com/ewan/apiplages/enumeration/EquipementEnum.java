package com.ewan.apiplages.enumeration;

public enum EquipementEnum {

    UN_LIT("Un lit"),
    DEUX_LITS("Deux lits"),
    UN_LIT_UN_FAUTEUIL("Un lit et un fauteuil"),
    UN_FAUTEUIL("Un fauteuil"),
    DEUX_FAUTEUILS("Deux fauteuils");
    private final String nom;
    EquipementEnum(String nom) {
        this.nom = nom;
    }
    public String getNom() {
        return nom;
    }
    public byte getNbDeLits() {
        return switch (this) {
            case UN_LIT, UN_LIT_UN_FAUTEUIL -> 1;
            case DEUX_LITS -> 2;
            case UN_FAUTEUIL, DEUX_FAUTEUILS -> 0;
        };
    }
    public byte getNbDeFauteuils() {
        return switch (this) {
            case UN_LIT, DEUX_LITS -> 0;
            case UN_LIT_UN_FAUTEUIL, UN_FAUTEUIL -> 1;
            case DEUX_FAUTEUILS -> 2;
        };
    }

    public static String deduceNom(byte nbDeLits,byte nbDeFauteuils) {

        if(nbDeLits == 2) return DEUX_LITS.nom;
        if(nbDeFauteuils == 2) return DEUX_FAUTEUILS.nom;
        if(nbDeLits == 0) return UN_FAUTEUIL.nom;
        if(nbDeFauteuils == 0) return UN_LIT.nom;
        return UN_LIT_UN_FAUTEUIL.nom;
    }


}
