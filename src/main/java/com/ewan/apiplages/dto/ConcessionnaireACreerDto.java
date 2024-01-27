package com.ewan.apiplages.dto;

public class ConcessionnaireACreerDto {
    private Long utilisateurId;
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private String numeroDeTelephone;

    public String getEmail() { return this.email; }
    public String getMotDePasse() { return this.motDePasse; }

    public String getNom() { return this.nom; }

    public String getNumeroDeTelephone() {return this.numeroDeTelephone;}
    public String getPrenom() { return this.prenom; }

}
