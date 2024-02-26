package com.ewan.apiplages.output;

import  com.ewan.apiplages.output.UtilisateurOutput;

public class LoginOutput {

    private final byte codeErreur;
    private final UtilisateurOutput utilisateurConnecte;

    public LoginOutput(byte codeErreur,UtilisateurOutput utilisateurConnecte) {
        this.codeErreur = codeErreur;
        this.utilisateurConnecte = utilisateurConnecte;
    }

    public byte getCodeErreur() { return this.codeErreur ; }
    public UtilisateurOutput getUtilisateurConnecte() { return this.utilisateurConnecte ; }
}
