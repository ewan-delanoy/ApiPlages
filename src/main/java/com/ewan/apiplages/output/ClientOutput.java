package com.ewan.apiplages.output;


import java.time.LocalDateTime;

public record ClientOutput(String nom, String prenom, String email,  PaysOutput pays,
                           LocalDateTime dateHeureInscription) {
}
