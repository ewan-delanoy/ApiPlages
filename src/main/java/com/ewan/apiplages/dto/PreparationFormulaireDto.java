package com.ewan.apiplages.dto;

import com.ewan.apiplages.entity.LienDeParente;

import java.util.List;

public class PreparationFormulaireDto {

    public List<ParasolDto> parasolsDisponibles;
    public List<EquipementDto> equipements;

    public List<LienDeParenteDto> liensDeParente;

    public List<PaysDto> paysEnvisageables;

    public PreparationFormulaireDto() {

    }

}
