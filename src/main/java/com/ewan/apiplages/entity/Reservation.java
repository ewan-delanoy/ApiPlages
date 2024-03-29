package com.ewan.apiplages.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import com.ewan.apiplages.output.*;
import com.ewan.apiplages.util.KeepCompilerQuiet;
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


    private String numeroCarte ;

    private byte moisExpiration ;

    private short anneeExpiration ;

    private String cryptogramme ;

    // Une réservation a un statut
    // Cette annotation va créer une clé étrangère
    // dans la table reservation
    @ManyToOne
    private Statut statut;

    @ManyToOne
    private LienDeParente lienDeParente;

    // No-args constructor demandé par JPA
    public Reservation() {
        super();
    }
    public Reservation(Client client,
                       LocalDate dateDebut,LocalDate dateFin,
                       LienDeParente lienDeParente,Statut statut,
                       String numeroCarte, byte moisExpiration, short anneeExpiration, String cryptogramme) {
        this.client = client ;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.lienDeParente = lienDeParente;
        this.statut = statut;
        this.numeroCarte = numeroCarte;
        this.moisExpiration = moisExpiration;
        this.anneeExpiration = anneeExpiration;
        this.cryptogramme = cryptogramme;

    }

    public ReservationOutput toOutput(List<Affectation> affectations) {

        List<AffectationOutput> affectationsOutput = new ArrayList<>();
        double totalAvantRemise = 0;
        for (Affectation affectation : affectations) {
            affectationsOutput.add(affectation.toOutput());
            totalAvantRemise += affectation.getEmplacement().getFile().getPrixJournalier() ;
        }
        double totalApresRemise = totalAvantRemise * this.lienDeParente.getCoefficient();
        Plage plage = affectations.get(0).getEmplacement().getFile().getPlage();
        return new ReservationOutput(
                this.reservationId,
                plage.toOutput(),this.dateDebut,this.dateFin,
                affectationsOutput,
                this.client.toOutput(),
                this.lienDeParente.toOutput(), this.statut.getNom(),
                this.numeroCarte, this.moisExpiration, this.anneeExpiration,
                totalAvantRemise,totalApresRemise
        );
    }


    public Long getReservationId() {return this.reservationId;}
    public Client getClient() {return this.client; }


    public LienDeParente getLienDeParente() {return this.lienDeParente; }

    public Statut getStatut() {return this.statut; }

    public void setStatut(Statut newStatut) {this.statut = newStatut; }

    public void keepCompilerQuiet() {
        if (this.reservationId < 0) {
            if(this.affectations.isEmpty()) {
                this.reservationId = KeepCompilerQuiet.doNotModifyLong(this.reservationId);
            }
            this.dateDebut = KeepCompilerQuiet.doNotModifyDate(this.dateDebut);
            this.dateFin = KeepCompilerQuiet.doNotModifyDate(this.dateFin);
            this.affectations = new ArrayList<>();
            this.cryptogramme = KeepCompilerQuiet.doNotModifyString(this.cryptogramme);
            this.cryptogramme = KeepCompilerQuiet.doNotModifyString(this.getLienDeParente().getNom());
            this.cryptogramme = KeepCompilerQuiet.doNotModifyString(this.getStatut().getNom());
        }
    }

}
