package com.ewan.apiplages.entity;

import com.ewan.apiplages.dto.ConcessionnaireDto;
import jakarta.persistence.Entity;


@Entity
public class Concessionnaire extends Utilisateur {

    private String numeroDeTelephone;

    // Le no-args constructor exigé par JPA
    protected Concessionnaire() {
        super();
    }
    public Concessionnaire(String nom, String prenom, String email, String motDePasse, String numeroDeTelephone) {
        super(nom,prenom,email,motDePasse);
        this.numeroDeTelephone = numeroDeTelephone;
    }

    public Concessionnaire(ConcessionnaireDto concessionnaireDto) {
        this(
                concessionnaireDto.getNom(),
                concessionnaireDto.getPrenom(),
                concessionnaireDto.getEmail(),
                concessionnaireDto.getMotDePasse(),
                concessionnaireDto.getNumeroDeTelephone());
    }

    public Long getUtilisateurId() {return this.utilisateurId; }
    public String getNom() {return this.nom; }
    public String getPrenom() {return this.prenom;}
    public String getEmail() {return this.email; }
    public String getMotDePasse() {return this.motDePasse; }
    public String getNumeroDeTelephone() {return this.numeroDeTelephone;}

}
