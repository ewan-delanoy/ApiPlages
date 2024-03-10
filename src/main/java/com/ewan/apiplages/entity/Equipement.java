package com.ewan.apiplages.entity;

import com.ewan.apiplages.enumeration.EquipementEnum;
import com.ewan.apiplages.output.EquipementOutput;
import com.ewan.apiplages.util.KeepCompilerQuiet;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Equipement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long equipementId;

    private byte nbDeLits;
    private byte nbDeFauteuils;

    // Constructeur où l'ID est géré automatiquement par JPA
    // Utilisé dans la phase de remplissage initiale des tables
    public Equipement(byte nbDeLits, byte nbDeFauteuils) {
        this();
        this.nbDeLits = nbDeLits;
        this.nbDeFauteuils = nbDeFauteuils;
    }

    // Constructeur a préferer dans tous les autres cas
    public Equipement(EquipementEnum equipementEnum) {

        this(equipementEnum.getNbDeLits (),
                equipementEnum.getNbDeFauteuils());
    }

    // Constructeur sans argument demandé par JPA
    protected Equipement() {
        super();
    }


    public EquipementOutput toOutput() {
        return new EquipementOutput(EquipementEnum.deduceNom(this.nbDeLits,this.nbDeFauteuils),
                this.nbDeLits,this.nbDeFauteuils);
    }


    /* public String getDescription() {
        String lit = this.nbDeLits==0?"":(this.nbDeLits==1?"1 lit":(this.nbDeLits)+" lits");
        String virgule = ( this.nbDeLits!=0 && this.nbDeFauteuils!=0)?", ":"";
        String fauteuil = this.nbDeFauteuils==0?"":(this.nbDeFauteuils==1?"1 fauteuil":(this.nbDeFauteuils)+" fauteuils");
        return lit + virgule  + fauteuil;
    } */


    public byte getNbDeLits() {return this.nbDeLits; }
    public byte getNbDeFauteuils() {return this.nbDeFauteuils; }

    public void keepCompilerQuiet() {
       this.equipementId = KeepCompilerQuiet.doNotModifyLong(this.equipementId);
    }


}

