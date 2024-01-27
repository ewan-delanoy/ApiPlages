package com.ewan.apiplages.dto;

import com.ewan.apiplages.entity.Plage;
import com.ewan.apiplages.entity.Statut;

public class StatutDto {
    private Long statutId;
    private String nom;

    public Long getStatutId() { return this.statutId; }
    public String getNom() { return this.nom; }

    public StatutDto(Statut statut) {
        this.statutId = statut.getStatutId();
        this.nom = statut.getNom();

    }
}
