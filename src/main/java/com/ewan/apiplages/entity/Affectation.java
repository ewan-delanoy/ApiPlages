package com.ewan.apiplages.entity;

import com.ewan.apiplages.output.AffectationOutput;
import jakarta.persistence.*;

@Entity
public class Affectation {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long affectationId;
    @ManyToOne
    private Emplacement emplacement;
    @ManyToOne
    private Equipement equipement;
    @ManyToOne
    private Reservation reservation;

    // No-args constructor demandé par JPA
    protected Affectation() {
        super();
    }
    public Affectation(Emplacement emplacement,Equipement equipement,Reservation reservation) {
        this();
        this.emplacement = emplacement;
        this.equipement = equipement;
        this.reservation = reservation;
    }

    public AffectationOutput toOutput() {
        return new AffectationOutput(
                this.emplacement.getFile().getNumero(),
                this.emplacement.getFile().getPrixJournalier(),
                this.emplacement.getNumEmplacement(),
                this.equipement.getNbDeLits(),
                this.equipement.getNbDeFauteuils()
        );

    }

    public Emplacement getEmplacement() {return this.emplacement; }

}
