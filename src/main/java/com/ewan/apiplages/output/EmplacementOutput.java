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

    public Long emplacementId() { return this. emplacementId; }
    public byte numEmplacement() { return this.numEmplacement ; }
    public byte numeroFile() { return this.numeroFile ; }
}
