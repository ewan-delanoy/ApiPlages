package com.ewan.apiplages.dto;


import com.ewan.apiplages.entity.Parasol;


import java.util.List;

public class EmplacementDto {
    private Long parasolId;
    private byte numEmplacement;
    private byte numeroFile;


    public EmplacementDto(Parasol parasol) {
        this.parasolId = parasol.getParasolId();
        this.numEmplacement = parasol.getNumEmplacement();
        this.numeroFile = parasol.getFile().getNumero();
    }

    public Long getParasolId() {return this.parasolId;}
    public byte getNumEmplacement() {return this.numEmplacement; }
    public byte getNumeroFile() {return this.numeroFile; }

}
