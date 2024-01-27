package com.ewan.apiplages.entity;

import java.util.List;

import com.ewan.apiplages.dto.ClientDto;
import com.ewan.apiplages.dto.ParasolDto;
import com.ewan.apiplages.input.ParasolOutput;
import com.ewan.apiplages.output.ClientOutput;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
// import jakarta.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
public class Parasol {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long parasolId;

    private byte numEmplacement;

    @ManyToOne
    private File file;

    // @ToString.Exclude
    @ManyToMany(mappedBy="parasols")
    private List<Reservation> reservations;

    @ManyToOne
    private Equipement equipement;

    // No-args constructor demandé par JPA
    protected Parasol() {
        super();
    }
    public Parasol(File file,byte numEmplacement) {
        this();
        this.numEmplacement = numEmplacement;
        this.file = file;
        this.equipement = null;
    }
    public Parasol(ParasolDto parasolDto) {
        this(new File(parasolDto.getFile()),
                parasolDto.getNumEmplacement());
        this.parasolId = parasolDto.getParasolId();
        this.equipement = new Equipement(parasolDto.getEquipement());
    }

    public ParasolOutput toOutput() {
        return new ParasolOutput(this.numEmplacement,this.file.getNumero(),
                this.equipement.getNbDeLits(),
                this.equipement.getNbDeFauteuils()
               );

    }

    public void setEquipement(Equipement newEquipement) {
        this.equipement = newEquipement;
    }

    @JsonIgnore
    public String getNumeroDeLaFile() {
        return String.valueOf(file.getNumero());
    }

    public String getNumeroEmplacementEtNumeroDeFile() {
        return numEmplacement + " en file " + file.getNumero();
    }

    public Long getParasolId() {return this.parasolId;}
    public byte getNumEmplacement() {return this.numEmplacement; }
    public File getFile() {return this.file; }

    public Equipement getEquipement() {return this.equipement; }

}