package com.ewan.apiplages.output;

import java.util.List;

public class ReservationOutput {
   
    private final List<AffectationOutput> affectations;
    private final ClientOutput client;
    private final PlageOutput plage;
    private final LienDeParenteOutput lienDeParente;
    private final String statutNom;

    public ReservationOutput(List<AffectationOutput> affectations,
                                ClientOutput client, PlageOutput plage,
                                LienDeParenteOutput lienDeParente, String statutNom) {
        this.affectations = affectations;
        this.client = client;
        this.plage = plage;
        this.lienDeParente = lienDeParente;
        this.statutNom = statutNom;
    }
    
    public List<AffectationOutput> affectations() { return this.affectations ; }
    public ClientOutput client() { return this.client ; }
    public PlageOutput plage() { return this.plage ; }
    public LienDeParenteOutput lienDeParente() { return this.lienDeParente ; }
    public String statutNom() { return this.statutNom ; }

}
