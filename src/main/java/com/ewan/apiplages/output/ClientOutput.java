package com.ewan.apiplages.output;


import java.time.LocalDateTime;

public class ClientOutput {

    private final String nom;
    private final String prenom;
    private final String email;
    private final PaysOutput pays;
    private final LocalDateTime dateHeureInscription;

    public ClientOutput(String nom, String prenom, String email,  PaysOutput pays,
                           LocalDateTime dateHeureInscription) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.pays = pays;
        this.dateHeureInscription = dateHeureInscription;
        
    }

    public String getNom() { return this.nom ; }
    public String getPrenom() { return this.prenom ; }
    public String getEmail() { return this.email ; }
    public PaysOutput getPays() { return this.pays ; }
    public LocalDateTime getDateHeureInscription() { return this.dateHeureInscription ; }
}
