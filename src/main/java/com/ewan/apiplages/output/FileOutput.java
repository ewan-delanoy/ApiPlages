package com.ewan.apiplages.output;

public class FileOutput {

    private final byte numero;
    private final double prixJournalier;

     public FileOutput(byte numero,double prixJournalier) {
        this.numero = numero;
        this.prixJournalier = prixJournalier;  
    }

    public byte getNumero() { return this.numero ; }
    public double getPrixJournalier() { return this.prixJournalier ; }
}
