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

    public List<ReservationOutput> getEnAttente() { return this.enAttente ; }
    public List<ReservationOutput> getAcceptees() { return this.acceptees ; }
    public List<ReservationOutput> getRefusees() { return this.refusees ; }
}
