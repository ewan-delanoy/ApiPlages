package com.ewan.apiplages.output;

public class EmplacementOutput {

    private final Long emplacementId;
    private final byte numEmplacement;
    private final byte numeroFile;

    public EmplacementOutput(Long emplacementId,byte numEmplacement, byte numeroFile) {
        this.emplacementId = emplacementId;
        this.numEmplacement = numEmplacement;
        this.numeroFile = numeroFile;
    }

    public Long getEmplacementId() { return this. emplacementId; }
    public byte getNumEmplacement() { return this.numEmplacement ; }
    public byte getNumeroFile() { return this.numeroFile ; }
}
