package com.ewan.apiplages.entity;


import com.ewan.apiplages.output.FileOutput;
import com.ewan.apiplages.util.KeepCompilerQuiet;
import jakarta.persistence.*;

@Entity
public class File {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long fileId;

    private byte numero;

    private double prixJournalier;

    @ManyToOne
    private Plage plage;


    // No-args constructor demandé par JPA
    public File() {
        super();
    }
    public File(Plage plage,byte numero) {
        this.plage = plage ;
        this.prixJournalier = (20-2*numero);
        this.numero = numero;
    }



    public FileOutput toOutput() {
        return new FileOutput(this.numero,this.prixJournalier);
    }
    // public Long getFileId() {return this.fileId;}
    public Plage getPlage() {
        return this.plage;
    }

    public double getPrixJournalier() {
        return this.prixJournalier;
    }

    public byte getNumero() {
        return this.numero;
    }

    public void keepCompilerQuiet() {
        this.fileId = KeepCompilerQuiet.doNotModifyLong(this.fileId);
    }
}

