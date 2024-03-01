package com.ewan.apiplages.output;

import java.util.List;

public record ReservationOutput(List<AffectationOutput> affectations,
                                ClientOutput client, PlageOutput plage,
                                LienDeParenteOutput lienDeParente, String statutNom) {
}
