package com.ewan.apiplages.output;

public class AffectationOutput {
    private final byte numeroFile;
    private final double prixJournalierFile;
    private final byte numEmplacement;
    private final byte nbDeLits;
    private final byte nbDeFauteuils;

    public AffectationOutput(byte numeroFile, double prixJournalierFile, byte numEmplacement,byte nbDeLits,byte nbDeFauteuils) {
        this.numeroFile = numeroFile;
        this.prixJournalierFile = prixJournalierFile;
        this.numEmplacement = numEmplacement;
        this.nbDeLits = nbDeLits ;
        this.nbDeFauteuils = nbDeFauteuils ;
        
    }

    public byte numeroFile() { return this.numeroFile ; }
    public double prixJournalierFile() { return this.prixJournalierFile ; }
    public byte numEmplacement() { return this.numEmplacement ; }
    public byte nbDeLits() {return this.nbDeLits; }
    public byte nbDeFauteuils() {return this.nbDeFauteuils; }
}
