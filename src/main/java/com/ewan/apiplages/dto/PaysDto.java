package com.ewan.apiplages.dto;

import com.ewan.apiplages.entity.Pays;

public class PaysDto {
    private String code;
    private String nom;

    public PaysDto(Pays pays) {
        this.code = pays.getCode();
        this.nom = pays.getNom();
    }
    public String getCode() { return this.code; }
    public String getNom() { return this.nom; }

}
