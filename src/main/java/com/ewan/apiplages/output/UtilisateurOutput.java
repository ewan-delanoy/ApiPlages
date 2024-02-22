package com.ewan.apiplages.output;


import java.time.LocalDateTime;

public class UtilisateurOutput {

    private final Long utilisateurId;
    private final String nom;
    private final String prenom;
    private final String email;
    private final PaysOutput pays;
    private final LocalDateTime dateHeureInscription;
    private final String numeroDeTelephone;
    private final String typeUtilisateur;


    public UtilisateurOutput(
        // champs communs à tous les utilisateurs
        Long utilisateurId,String nom,String prenom,String email,
        // champs spécifiques aux clients
        PaysOutput pays,LocalDateTime dateHeureInscription,
        // champs spécifiques aux concessionnaires
        String numeroDeTelephone,String typeUtilisateur
        ) {
        this.utilisateurId = utilisateurId;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.pays = pays;
        this.dateHeureInscription = dateHeureInscription;
        this.numeroDeTelephone = numeroDeTelephone;
        this.typeUtilisateur = typeUtilisateur;
    }

    public Long utilisateurId() { return this.utilisateurId ; }
    public String nom() { return this.nom ; }
    public String prenom() { return this.prenom ; }
    public String email() { return this.email ; }
    public PaysOutput pays() { return this.pays ; }
    public LocalDateTime dateHeureInscription() { return this.dateHeureInscription ; }
    public String numeroDeTelephone() { return this.numeroDeTelephone ; }
    public String typeUtilisateur() { return this.typeUtilisateur ; }

}
