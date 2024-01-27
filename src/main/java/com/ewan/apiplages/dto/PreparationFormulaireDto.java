package com.ewan.apiplages.dto;

import java.util.List;

public class PreparationFormulaireDto {

    public List<EmplacementDto> emplacementsDisponibles;
    public List<EquipementDto> equipements;
    public List<LienDeParenteDto> liensDeParente;
    public List<PaysDto> paysEnvisageables;

    public PreparationFormulaireDto(List<EmplacementDto> emplacementsDisponibles,List<EquipementDto> equipements,List<LienDeParenteDto> liensDeParente,List<PaysDto> paysEnvisageables) {
        this.emplacementsDisponibles = emplacementsDisponibles;
        this.equipements = equipements;
        this.liensDeParente = liensDeParente;
        this.paysEnvisageables = paysEnvisageables;
    }

    @Override
    public String toString() {
        StringBuilder reponse = new StringBuilder("[");
        for(EmplacementDto emplacementDto:this.emplacementsDisponibles) {
            reponse.append(emplacementDto.toString()).append("\n");
        }
        reponse.append("]");
        return (reponse.toString());
    }

}
