package com.ewan.apiplages.entity;

import java.time.LocalDateTime;
import java.util.List;


import com.ewan.apiplages.input.ClientInput;
import com.ewan.apiplages.output.ClientOutput;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


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



    public Client(ClientInput clientInput) {
        super(clientInput.nom(),
                clientInput.prenom(),
                clientInput.email(),
                clientInput.motDePasse());
        this.pays = new Pays(clientInput.paysInput());
        this.dateHeureInscription = clientInput.dateHeureInscription();

    }

    public ClientOutput toOutput() {
        return new ClientOutput(this.nom,this.prenom,this.email,
                this.pays.toOutput(),this.dateHeureInscription);

    }


}