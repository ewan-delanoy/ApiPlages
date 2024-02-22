package com.ewan.apiplages.output;

public class FileOutput {

    private final byte numero;
    private final double prixJournalier;

     public FileOutput(byte numero,double prixJournalier) {
        this.numero = numero;
        this.prixJournalier = prixJournalier;  
    }

    public byte numero() { return this.numero ; }
    public double prixJournalier() { return this.prixJournalier ; }
}
