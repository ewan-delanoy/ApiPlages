package com.ewan.apiplages.dto;

import java.time.LocalDateTime;

public class ClientACreerDto {

    private String nom;
    private String prenom;

    private String email;

    private String motDePasse;
    private PaysDto paysDto;
    private LocalDateTime dateHeureInscription;

    public String getEmail() { return this.email; }
    public String getMotDePasse() { return this.motDePasse; }

    public String getNom() { return this.nom; }
    public PaysDto getPays() { return this.paysDto; }

    public String getPrenom() { return this.prenom; }
}
