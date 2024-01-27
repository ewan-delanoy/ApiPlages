package com.ewan.apiplages.entity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import com.ewan.apiplages.dto.ParasolDto;
import com.ewan.apiplages.dto.ReservationACreerDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
// import jakarta.validation.constraints.NotNull;
// import jakarta.validation.constraints.Size;


import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;


@Entity

public class Reservation {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long reservationId;

    @ManyToMany
    // @Size(min=1, message="La réservation doit inclure au minimum {min} parasol")
    private List<Parasol> parasols;

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
    public Reservation(Client client,List<Parasol> parasols,
                       LocalDate dateDebut,LocalDate dateFin,
                       LienDeParente lienDeParente,Statut statut) {
        this.client = client ;
        this.parasols = parasols;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.lienDeParente = lienDeParente;
        this.statut = statut;
        this.montantAReglerEnEuros = 100;
    }

    public Reservation(ReservationACreerDto reservationACreerDto) {
        List<ParasolDto> parasolsDto = reservationACreerDto.getParasols();
        List<Parasol> parasols = Arrays.asList();
        for (ParasolDto parasolDto : parasolsDto) {
            parasols.add(new Parasol(parasolDto));
        }
        this.client = new Client(reservationACreerDto.getClient());
        this.parasols = parasols;
        this.dateDebut = reservationACreerDto.getDateDebut();
        this.dateFin = reservationACreerDto.getDateFin();
        this.lienDeParente = new LienDeParente(reservationACreerDto.getLienDeParente());

    }

    public Long getReservationId() {return this.reservationId;}
    public Client getClient() {return this.client; }
    public List<Parasol> getParasols() {return this.parasols; }
    public LocalDate getDateDebut() {return this.dateDebut; }
    public LocalDate getDateFin() {return this.dateFin; }

    public LienDeParente getLienDeParente() {return this.lienDeParente; }

    public Statut getStatut() {return this.statut; }

    public void setStatut(Statut newStatut) {this.statut = newStatut; }

}