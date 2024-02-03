package com.ewan.apiplages.input;



import java.time.LocalDate;
import java.util.List;

public record ReservationInput (Long clientId, Long plageId,
                                List<AffectationInput> affectations, LocalDate dateDebut, LocalDate dateFin,
                                String lienDeParenteNom) {

}
