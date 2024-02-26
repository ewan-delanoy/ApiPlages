package com.ewan.apiplages.output;

public class ConcessionnaireOutput {
    private final String nom;
    private final String prenom;
    private final String email;
    private final String numeroDeTelephone;

    public ConcessionnaireOutput(String nom, String prenom, String email,
                                    String numeroDeTelephone) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.numeroDeTelephone = numeroDeTelephone;
    }

    public String getNom() { return this.nom ; }
    public String getPrenom() { return this.prenom ; }
    public String getEmail() { return this.email ; }
    public String getNumeroDeTelephone() { return this.numeroDeTelephone ; }
}
