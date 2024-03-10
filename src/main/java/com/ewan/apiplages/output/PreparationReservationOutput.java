package com.ewan.apiplages.output;

import java.util.List;

public record PreparationReservationOutput(List<ParasolOutput> parasols, List<EquipementOutput> equipements, List<LienDeParenteOutput> liensDeParente) {
}