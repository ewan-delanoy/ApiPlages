package com.ewan.apiplages.output;

public class EquipementOutput {

    private final byte nbDeLits;
    private final byte nbDeFauteuils;

    public EquipementOutput(byte nbDeLits,byte nbDeFauteuils) {
        this.nbDeLits = nbDeLits ;
        this.nbDeFauteuils = nbDeFauteuils ;
        
    }

    public byte nbDeLits() {return this.nbDeLits; }
    public byte nbDeFauteuils() {return this.nbDeFauteuils; }
}
