package com.ewan.apiplages.entity;

import com.ewan.apiplages.input.ConcessionnaireInput;
import com.ewan.apiplages.output.ConcessionnaireOutput;
import com.ewan.apiplages.output.UtilisateurOutput;
import jakarta.persistence.Entity;
import org.springframework.security.crypto.password.PasswordEncoder;


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
                this.numeroDeTelephone
        );
    }

    public Concessionnaire(ConcessionnaireInput concessionnaireInput, PasswordEncoder encoder) {
        super(concessionnaireInput.nom(),
                concessionnaireInput.prenom(),
                concessionnaireInput.email(),
                encoder.encode(concessionnaireInput.motDePasse()));
        this.numeroDeTelephone = concessionnaireInput.numeroDeTelephone();
    }

    public ConcessionnaireOutput toOutput() {
        return new ConcessionnaireOutput(this.nom,this.prenom,this.email,
               this.numeroDeTelephone);

    }


}
