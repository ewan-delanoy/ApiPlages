package com.ewan.apiplages.entity;

import com.ewan.apiplages.output.AffectationOutput;
import com.ewan.apiplages.util.KeepCompilerQuiet;
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
    public Affectation() {
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
                this.emplacement.toOutput(),
                this.equipement.toOutput()
        );

    }

    public Emplacement getEmplacement() {return this.emplacement; }

    public void keepCompilerQuiet() {
       if (this.affectationId < 0) {
           this.affectationId = KeepCompilerQuiet.doNotModifyLong(this.reservation.getReservationId());
       }
    }

}
