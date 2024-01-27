package com.ewan.apiplages.dto;

import com.ewan.apiplages.entity.Equipement;

public class EquipementDto {
    private Long equipementId;
    private byte nbDeLits;
    private byte nbDeFauteuils;

    public EquipementDto(Equipement equipement) {
        this.equipementId = equipement.getEquipementId();
        this.nbDeLits = equipement.getNbDeLits();
        this.nbDeFauteuils = equipement.getNbDeFauteuils();
    }

    public Long getEquipementId() { return this.equipementId; }
    public byte getNbDeLits() { return this.nbDeLits; }
    public byte getNbDeFauteuils() { return this.nbDeFauteuils; }

}
