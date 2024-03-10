package com.ewan.apiplages.input;

import java.time.LocalDate;

public record PreparationReservationInput(Long plageId, LocalDate dateDebut, LocalDate dateFin) {
}
