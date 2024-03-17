package com.ewan.apiplages.output;

import java.time.LocalDate;
import java.util.List;

public record ReservationOutput(
        Long reservationId,
        PlageOutput plage,
        LocalDate dateDebut,
        LocalDate dateFin,
        List<AffectationOutput> affectations,
        ClientOutput client,
        LienDeParenteOutput lienDeParente, String statutNom,
        String numeroCarte,
        byte moisExpiration,
        short anneeExpiration,
        double totalAvantRemise,
        double totalApresRemise) {
}
