package com.ewan.apiplages.dto;

import com.ewan.apiplages.entity.Equipement;
import com.ewan.apiplages.entity.File;

public class FileDto {
    private Long fileId;

    private byte numero;

    private double prixJournalier;

    private PlageDto plageDto ;

    public FileDto(File file) {
        this.fileId = file.getFileId();
        this.numero = file.getNumero();
        this.prixJournalier = file.getPrixJournalier();
        this.plageDto = new PlageDto(file.getPlage());
    }

    public Long getFileId() { return this.fileId; }
    public byte getNumero() { return this.numero; }
    public double getPrixJournalier() { return this.prixJournalier; }
    public PlageDto getPlage() { return this.plageDto; }
}
