package com.ewan.apiplages.output;

import java.util.List;

public record PreparationReservationOutput(byte nbDeFiles, byte nbDemplacementsParFile, List<ParasolOutput> parasols, List<FileOutput> files, List<EquipementOutput> equipements, List<LienDeParenteOutput> liensDeParente) {
}
