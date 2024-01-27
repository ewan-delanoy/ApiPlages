package com.ewan.apiplages.input;

import java.time.LocalDateTime;

public record ConcessionnaireInput(String nom, String prenom, String email, String motDePasse,
                                   String numeroDeTelephone) {
}
