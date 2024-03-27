package com.ewan.apiplages.input;
import java.util.List;

public record ReservationInput (Long clientId, Long plageId,
                                List<AffectationInput> affectations,
                                String dateDebut,
                                String dateFin,
                                String lienDeParenteNom,
                                String numeroCarte, byte moisExpiration, short anneeExpiration, String cryptogramme) {

}
