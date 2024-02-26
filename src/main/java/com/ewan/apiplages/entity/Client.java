package com.ewan.apiplages.entity;

import java.time.LocalDateTime;


import com.ewan.apiplages.enumeration.TypeUtilisateurEnum;
import com.ewan.apiplages.input.ClientRegistrationInput;
import com.ewan.apiplages.output.ClientOutput;
import com.ewan.apiplages.output.UtilisateurOutput;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import com.ewan.apiplages.util.crypto.BCryptPasswordEncoder;


@Entity
public class Client extends Utilisateur {

    @ManyToOne
    private Pays pays;

    // @OneToMany(mappedBy="client")
    // private List<Reservation> reservations;

    @Column(updatable = false)
    private LocalDateTime dateHeureInscription;


    // No-args constructor demandé par JPA
    protected Client() {
        super();
    }

    public Client(String nom, String prenom, String email, String motDePasse, Pays pays) {
        super(nom,prenom,email,motDePasse);
        this.dateHeureInscription = LocalDateTime.now();
        this.pays = pays;
    }



    public Client(ClientRegistrationInput clientRegistrationInput, BCryptPasswordEncoder encoder) {
        super(clientRegistrationInput.getNom(),
                clientRegistrationInput.getPrenom(),
                clientRegistrationInput.getEmail(),
                encoder.encode(clientRegistrationInput.getMotDePasse()));
        this.pays = new Pays(clientRegistrationInput.getPaysInput());
        this.dateHeureInscription = LocalDateTime.now();

    }

    public ClientOutput toOutput() {
        return new ClientOutput(this.nom,this.prenom,this.email,
                this.pays.toOutput(),this.dateHeureInscription);

    }

    public UtilisateurOutput clientToUtilisateurOutput() {
        return new  UtilisateurOutput(
                this.utilisateurId,this.nom,this.prenom,this.email,
                this.pays.toOutput(),this.dateHeureInscription,
                null, TypeUtilisateurEnum.CLIENT.getNom()
        );
    }




}
