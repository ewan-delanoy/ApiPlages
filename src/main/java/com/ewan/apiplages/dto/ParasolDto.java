package com.ewan.apiplages.dto;


import com.ewan.apiplages.entity.Parasol;


public class ParasolDto {
    private Long parasolId;
    private byte numEmplacement;
    private FileDto fileDto;

    private EquipementDto equipementDto;

    public ParasolDto(Parasol parasol) {
        this.parasolId = parasol.getParasolId();
        this.numEmplacement = parasol.getNumEmplacement();
        this.equipementDto = new EquipementDto(parasol.getEquipement());
        this.fileDto = new FileDto(parasol.getFile());
    }

    public Long getParasolId() {return this.parasolId;}
    public byte getNumEmplacement() {return this.numEmplacement; }
    public FileDto getFile() {return this.fileDto; }

    public EquipementDto getEquipement() {return this.equipementDto; }
}