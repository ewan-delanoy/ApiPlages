package com.ewan.apiplages.entity;


import com.ewan.apiplages.enumeration.TypeUtilisateurEnum;
import com.ewan.apiplages.output.ConcessionnaireOutput;
import com.ewan.apiplages.output.UtilisateurOutput;
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

    public UtilisateurOutput concessionnaireToUtilisateurOutput() {
        return new  UtilisateurOutput(
                // champs communs à tous les utilisateurs
                this.utilisateurId,this.nom,this.prenom,this.email,
                null,null,
                this.numeroDeTelephone, TypeUtilisateurEnum.CONCESSIONNAIRE.getNom()
        );
    }

    public ConcessionnaireOutput toOutput() {
        return new ConcessionnaireOutput(this.utilisateurId,this.nom,this.prenom,this.email,
               this.numeroDeTelephone);

    }


}
