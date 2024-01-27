package com.ewan.apiplages.dto;


import com.ewan.apiplages.entity.Plage;

public class PlageDto {
    private Long plageId;
    private String nom;
    private ConcessionnaireDto concessionnaireDto;

    // getters

    public Long getPlageId() { return this.plageId; }
    public String getNom() { return this.nom; }
    public ConcessionnaireDto getConcessionnaire() { return this.concessionnaireDto; }

    public PlageDto(Plage plage) {
        this.plageId = plage.getPlageId();
        this.nom = plage.getNom();
        this.concessionnaireDto = new ConcessionnaireDto(plage.getConcessionnaire());

    }
}
