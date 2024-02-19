package com.ewan.apiplages.output;


import java.time.LocalDateTime;

public record UtilisateurOutput(
        // champs communs à tous les utilisateurs
        Long utilisateurId,String nom,String prenom,String email,
        // champs spécifiques aux clients
        PaysOutput pays,LocalDateTime dateHeureInscription,
        // champs spécifiques aux concessionnaires
        String numeroDeTelephone,String typeUtilisateur
        ) {
}
