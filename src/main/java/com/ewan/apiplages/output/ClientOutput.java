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

    public String nom() { return this.nom ; }
    public String prenom() { return this.prenom ; }
    public String email() { return this.email ; }
    public PaysOutput pays() { return this.pays ; }
    public LocalDateTime dateHeureInscription() { return this.dateHeureInscription ; }                        
}
