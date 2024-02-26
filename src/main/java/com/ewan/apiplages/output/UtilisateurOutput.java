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

    public Long getUtilisateurId() { return this.utilisateurId ; }
    public String getNom() { return this.nom ; }
    public String getPrenom() { return this.prenom ; }
    public String getEmail() { return this.email ; }
    public PaysOutput getPays() { return this.pays ; }
    public LocalDateTime getDateHeureInscription() { return this.dateHeureInscription ; }
    public String getNumeroDeTelephone() { return this.numeroDeTelephone ; }
    public String getTypeUtilisateur() { return this.typeUtilisateur ; }

}
