package com.ewan.apiplages.entity;

import java.time.LocalDateTime;


import com.ewan.apiplages.input.ClientRegistrationInput;
import com.ewan.apiplages.output.ClientOutput;
import com.ewan.apiplages.output.UtilisateurOutput;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import org.springframework.security.crypto.password.PasswordEncoder;


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



    public Client(ClientRegistrationInput clientRegistrationInput, PasswordEncoder encoder) {
        super(clientRegistrationInput.nom(),
                clientRegistrationInput.prenom(),
                clientRegistrationInput.email(),
                encoder.encode(clientRegistrationInput.motDePasse()));
        this.pays = new Pays(clientRegistrationInput.paysInput());
        this.dateHeureInscription = clientRegistrationInput.dateHeureInscription();

    }

    public ClientOutput toOutput() {
        return new ClientOutput(this.nom,this.prenom,this.email,
                this.pays.toOutput(),this.dateHeureInscription);

    }

    public UtilisateurOutput clientToUtilisateurOutput() {
        return new  UtilisateurOutput(
                this.utilisateurId,this.nom,this.prenom,this.email,
                this.pays.toOutput(),this.dateHeureInscription,
                null
        );
    }




}