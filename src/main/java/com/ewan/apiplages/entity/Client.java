package com.ewan.apiplages.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.ewan.apiplages.dto.ClientDto;
import com.ewan.apiplages.dto.PaysDto;
import com.ewan.apiplages.input.ClientInput;
import com.ewan.apiplages.output.ClientOutput;
import com.ewan.apiplages.output.PaysOutput;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
// import jakarta.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Client extends Utilisateur {

    @ManyToOne
    private Pays pays;

    @OneToMany(mappedBy="client")
    @JsonIgnore
    private List<Reservation> reservations;

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

    public Client(ClientDto clientDto) {
        this(clientDto.getNom(),
                clientDto.getPrenom(),
                clientDto.getEmail(),
                clientDto.getMotDePasse(),
                new Pays(clientDto.getPays()));
        this.utilisateurId = clientDto.getUtilisateurId();

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
        return new ClientOutput(this.nom,this.prenom,this.email,this.motDePasse,
                this.pays.toOutput(),this.dateHeureInscription);

    }

    public Long getUtilisateurId() {return this.utilisateurId; }
    public String getNom() {return this.nom; }
    public String getPrenom() {return this.prenom;}
    public String getEmail() {return this.email; }
    public String getMotDePasse() {return this.motDePasse; }
    public Pays getPays() {return this.pays; }
    public LocalDateTime getDateHeureInscription() {return this.dateHeureInscription;}
}