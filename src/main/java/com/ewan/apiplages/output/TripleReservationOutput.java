package com.ewan.apiplages.output;

import java.util.List;

public record TripleReservationOutput(List<ReservationOutput> enAttente,
                                      List<ReservationOutput> acceptees,
                                      List<ReservationOutput> refusees
                                      ) {
}
