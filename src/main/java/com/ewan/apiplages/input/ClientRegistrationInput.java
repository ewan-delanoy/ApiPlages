package com.ewan.apiplages.input;

public record ClientRegistrationInput(String nom, String prenom, String email, String motDePasse,
                                      PaysInput paysInput) {
}
