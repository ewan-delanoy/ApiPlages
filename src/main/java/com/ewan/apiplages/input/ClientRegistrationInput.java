package com.ewan.apiplages.input;

import java.time.LocalDateTime;

public record ClientRegistrationInput(String nom, String prenom, String email, String motDePasse,
                                      PaysInput paysInput, LocalDateTime dateHeureInscription) {
}
