package com.ewan.apiplages.output;

import java.time.LocalDateTime;

public record ConcessionnaireOutput(String nom, String prenom, String email, String motDePasse,
                                    String numeroDeTelephone) {
}
