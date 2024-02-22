package com.ewan.apiplages.output;

import  com.ewan.apiplages.output.UtilisateurOutput;

public class LoginOutput {

    private final byte codeErreur;
    private final UtilisateurOutput utilisateurConnecte;

    public LoginOutput(byte codeErreur,UtilisateurOutput utilisateurConnecte) {
        this.codeErreur = codeErreur;
        this.utilisateurConnecte = utilisateurConnecte;
    }

    public byte codeErreur() { return this.codeErreur ; }
    public UtilisateurOutput utilisateurConnecte() { return this.utilisateurConnecte ; }
}
