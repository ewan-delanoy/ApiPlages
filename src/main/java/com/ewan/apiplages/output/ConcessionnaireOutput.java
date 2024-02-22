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

    public String nom() { return this.nom ; }
    public String prenom() { return this.prenom ; }
    public String email() { return this.email ; }
    public String numeroDeTelephone() { return this.numeroDeTelephone ; }
}
