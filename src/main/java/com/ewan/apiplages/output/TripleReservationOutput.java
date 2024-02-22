package com.ewan.apiplages.output;

import java.util.List;

public class TripleReservationOutput {
    private final List<ReservationOutput> enAttente;
    private final List<ReservationOutput> acceptees;
    private final List<ReservationOutput> refusees;

    public TripleReservationOutput(List<ReservationOutput> enAttente,
                                      List<ReservationOutput> acceptees,
                                      List<ReservationOutput> refusees
                                      ) {
        this.enAttente = enAttente;
        this.acceptees = acceptees;
        this.refusees = refusees;
    }

    public List<ReservationOutput> enAttente() { return this.enAttente ; }
    public List<ReservationOutput> acceptees() { return this.acceptees ; }
    public List<ReservationOutput> refusees() { return this.refusees ; }
}
