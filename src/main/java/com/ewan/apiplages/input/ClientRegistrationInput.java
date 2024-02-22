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

    public String nom() { return this.nom ; }
    public String prenom() { return this.prenom ; }
    public String email() { return this.email ; }
    public String motDePasse() { return this.motDePasse ; }
    public PaysInput paysInput() { return this.paysInput ; }

}
