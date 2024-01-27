package com.ewan.apiplages.entity;

import java.util.List;

import com.ewan.apiplages.dto.FileDto;
import com.ewan.apiplages.input.ConcessionnaireInput;
import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
public class File {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long fileId;

    private byte numero;

    private double prixJournalier;

    @OneToMany(mappedBy="file")
    private List<Parasol> parasols;

    @ManyToOne
    private Plage plage;


    // No-args constructor demandé par JPA
    protected File() {
        super();
    }
    public File(Plage plage,byte numero) {
        this.plage = plage ;
        this.prixJournalier = (double) (20-2*numero);
        this.numero = numero;
    }

    public File(FileDto fileDto) {
        this(new Plage(fileDto.getPlage ()),
                fileDto.getNumero());
        this.fileId = fileDto.getFileId();
    }

    public String getDescription() {
        return "F" + this.numero;
    }

    public Long getFileId() {
        return this.fileId;
    }
    public Plage getPlage() {
        return this.plage;
    }

    public double getPrixJournalier() {
        return this.prixJournalier;
    }

    public byte getNumero() {
        return this.numero;
    }
}

