package com.ewan.apiplages.util.crypto;

public enum BCryptVersion {
    $2A("$2a"),
    $2Y("$2y"),
    $2B("$2b");

    private final String version;

    private BCryptVersion(String version) {
        this.version = version;
    }

    public String getVersion() {
        return this.version;
    }
}
