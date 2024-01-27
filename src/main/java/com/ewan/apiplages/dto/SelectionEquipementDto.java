package com.ewan.apiplages.dto;

import com.ewan.apiplages.entity.Parasol;

public class SelectionEquipementDto {
    private Long parasolId;
    private byte nbDeLits;
    private byte nbDeFauteuils;

    public SelectionEquipementDto(Long parasolId,byte nbDeLits,byte nbDeFauteuils) {
        this.parasolId = parasolId;
        this.nbDeLits = nbDeLits;
        this.nbDeFauteuils = nbDeFauteuils;
    }

    public Long getParasolId() { return this.parasolId; }
    public byte getNbDeLits() { return this.nbDeLits; }
    public byte getNbDeFauteuils() { return this.nbDeFauteuils; }

}
