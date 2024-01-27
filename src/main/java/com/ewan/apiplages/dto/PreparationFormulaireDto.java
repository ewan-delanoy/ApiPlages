package com.ewan.apiplages.dto;

import java.util.List;

public class PreparationFormulaireDto {

    public List<ParasolDto> parasols;
    public List<EquipementDto> equipements;
    public List<LienDeParenteDto> liensDeParente;
    public List<PaysDto> paysEnvisageables;

    public PreparationFormulaireDto(List<ParasolDto> parasols,List<EquipementDto> equipements,List<LienDeParenteDto> liensDeParente,List<PaysDto> paysEnvisageables) {
        this.parasols = parasols;
        this.equipements = equipements;
        this.liensDeParente = liensDeParente;
        this.paysEnvisageables = paysEnvisageables;
    }

}
