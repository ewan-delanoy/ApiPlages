package com.ewan.apiplages.dto;


import com.ewan.apiplages.entity.Concessionnaire;

public class ConcessionnaireDto {
    private Long utilisateurId;
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private String numeroDeTelephone;

    public ConcessionnaireDto(Concessionnaire concessionnaire) {
        this.utilisateurId = concessionnaire.getUtilisateurId();
        this.nom = concessionnaire.getNom();
        this.prenom = concessionnaire.getPrenom();
        this.email = concessionnaire.getEmail();
        this.motDePasse = concessionnaire.getMotDePasse();
        this.numeroDeTelephone = concessionnaire.getNumeroDeTelephone();
    }

    public String getEmail() { return this.email; }
    public String getMotDePasse() { return this.motDePasse; }

    public String getNom() { return this.nom; }

    public String getNumeroDeTelephone() {return this.numeroDeTelephone;}
    public String getPrenom() { return this.prenom; }

}
