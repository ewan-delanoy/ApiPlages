package com.ewan.apiplages.entity;

import com.ewan.apiplages.output.EmplacementOutput;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Emplacement {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long emplacementId;

    private byte numEmplacement;

    @ManyToOne
    private File file;

    @OneToMany(mappedBy="emplacement")
    private List<Affectation> affectations;

    // No-args constructor demandé par JPA
    protected Emplacement() {
        super();
    }
    public Emplacement(File file,byte numEmplacement) {
        this();
        this.numEmplacement = numEmplacement;
        this.file = file;
    }

    public EmplacementOutput toOutput() {
        return new EmplacementOutput(this.file.toOutput(), this.numEmplacement);
    }

    public File getFile() {return this.file; }

    public byte getNumEmplacement() {return this.numEmplacement; }

    public Long getEmplacementId() {return this.emplacementId;}

}
