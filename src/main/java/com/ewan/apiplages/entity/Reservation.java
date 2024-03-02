package com.ewan.apiplages.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import com.ewan.apiplages.output.*;
import jakarta.persistence.*;


import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;


@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long reservationId;

    @OneToMany(mappedBy="reservation", fetch=FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Affectation> affectations;

    @ManyToOne
    // @NotNull(message="Merci de préciser le client")
    private Client client;

    @DateTimeFormat(iso=ISO.DATE)
    // @NotNull(message="Merci de préciser la date de début de la réservation")
    private LocalDate dateDebut;

    @DateTimeFormat(iso=ISO.DATE)
    private LocalDate dateFin;

    private double montantAReglerEnEuros;
    private String numeroCarte ;

    private byte moisExpiration ;

    private short anneeExpiration ;

    private String cryptogramme ;

    @Lob  // Large Object
    private String remarques;

    // Une réservation a un statut
    // Cette annotation va créer une clé étrangère
    // dans la table reservation
    @ManyToOne
    private Statut statut;

    @ManyToOne
    private LienDeParente lienDeParente;

    // No-args constructor demandé par JPA
    protected Reservation() {
        super();
    }
    public Reservation(Client client,
                       LocalDate dateDebut,LocalDate dateFin,
                       LienDeParente lienDeParente,Statut statut) {
        this.client = client ;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.lienDeParente = lienDeParente;
        this.statut = statut;
        this.montantAReglerEnEuros = 100;
    }

    public ReservationOutput toOutput(List<Affectation> affectations) {

        List<AffectationOutput> affectationsOutput = new ArrayList<>();
        for (Affectation affectation : affectations) {
            affectationsOutput.add(affectation.toOutput());
        }
        Plage plage = affectations.get(0).getEmplacement().getFile().getPlage();
        return new ReservationOutput(
                this.reservationId,
                plage.toOutput(),this.dateDebut,this.dateFin,
                affectationsOutput,
                this.client.toOutput(),
                this.lienDeParente.toOutput(), this.statut.getNom()
        );
    }


    public Long getReservationId() {return this.reservationId;}
    public Client getClient() {return this.client; }

    public LocalDate getDateDebut() {return this.dateDebut; }
    public LocalDate getDateFin() {return this.dateFin; }

    public LienDeParente getLienDeParente() {return this.lienDeParente; }

    public Statut getStatut() {return this.statut; }

    public void setStatut(Statut newStatut) {this.statut = newStatut; }

}
