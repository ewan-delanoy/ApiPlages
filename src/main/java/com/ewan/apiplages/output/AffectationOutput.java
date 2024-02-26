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

    public byte getNumeroFile() { return this.numeroFile ; }
    public double getPrixJournalierFile() { return this.prixJournalierFile ; }
    public byte getNumEmplacement() { return this.numEmplacement ; }
    public byte getNbDeLits() {return this.nbDeLits; }
    public byte getNbDeFauteuils() {return this.nbDeFauteuils; }
}
