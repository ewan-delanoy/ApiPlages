package com.ewan.apiplages.input;

import java.util.List;

public class AffectationInput {

    private final Long emplacementId;
    private final byte nbDeLits;
    private final byte nbDeFauteuils;

    public AffectationInput(Long emplacementId, byte nbDeLits, byte nbDeFauteuils) {
        this.emplacementId = emplacementId ;
        this.nbDeLits = nbDeLits ;
        this.nbDeFauteuils = nbDeFauteuils ;
    }
    public Long getEmplacementId() { return this.emplacementId; }
    public byte getNbDeLits() {return this.nbDeLits; }
    public byte getNbDeFauteuils() {return this.nbDeFauteuils; }


}
