package com.ewan.apiplages.input;



import java.time.LocalDate;
import java.util.List;

public class ReservationInput {

    private final Long clientId;
    private final Long plageId;
    private final List<AffectationInput> affectations;
    private final LocalDate dateDebut;
    private final LocalDate dateFin;
    private final String lienDeParenteNom;

    public ReservationInput(Long clientId, Long plageId,
                                List<AffectationInput> affectations, LocalDate dateDebut, LocalDate dateFin,
                                String lienDeParenteNom) {
        this.clientId = clientId;
        this.plageId = plageId;
        this.affectations = affectations;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.lienDeParenteNom = lienDeParenteNom;
    }

    public Long clientId() { return this.clientId ; }
    public Long plageId() { return this.plageId ; }
    public List<AffectationInput> affectations() { return this.affectations ; }
    public LocalDate dateDebut() { return this.dateDebut ; }
    public LocalDate dateFin() { return this.dateFin ; }
    public String lienDeParenteNom() { return this.lienDeParenteNom ; }
                              

}
