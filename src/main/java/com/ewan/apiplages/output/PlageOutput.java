package com.ewan.apiplages.output;

public class PlageOutput {

    private final Long plageId;
    private final String nom;
    private final ConcessionnaireOutput concessionnaire;

    public PlageOutput(Long plageId, String nom,ConcessionnaireOutput concessionnaire) {
        this.plageId = plageId;
        this.nom = nom;
        this.concessionnaire = concessionnaire;
    }

    public Long plageId() { return this.plageId ; }
    public String nom() { return this.nom ; }
    public ConcessionnaireOutput concessionnaire() { return this.concessionnaire ; }
}
