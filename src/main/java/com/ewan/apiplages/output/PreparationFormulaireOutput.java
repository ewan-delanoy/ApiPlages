package com.ewan.apiplages.output;

import java.util.List;

public record PreparationFormulaireOutput(List<ParasolOutput> emplacementsMarques, List<EquipementOutput> equipements, List<LienDeParenteOutput> liensDeParente, List<PaysOutput> paysEnvisageables) {
}
