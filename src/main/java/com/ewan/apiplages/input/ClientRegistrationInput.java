package com.ewan.apiplages.input;

public class ClientRegistrationInput {

    private final String nom;
    private final String prenom;
    private final String email;
    private final String motDePasse;
    private final PaysInput paysInput;
    
    public ClientRegistrationInput(String nom, String prenom, String email, String motDePasse, PaysInput paysInput) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.paysInput = paysInput;
       
    }

    public String getNom() { return this.nom ; }
    public String getPrenom() { return this.prenom ; }
    public String getEmail() { return this.email ; }
    public String getMotDePasse() { return this.motDePasse ; }
    public PaysInput getPaysInput() { return this.paysInput ; }

}
