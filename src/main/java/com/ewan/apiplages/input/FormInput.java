package com.ewan.apiplages.input;

import java.time.LocalDate;

public record FormInput(Long plageId, LocalDate dateDebut, LocalDate dateFin) {
}
