package com.ewan.apiplages.dto;

import com.ewan.apiplages.entity.Client;


import java.time.LocalDateTime;

public class ClientDto {
    private Long utilisateurId;
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private PaysDto paysDto;
    private LocalDateTime dateHeureInscription;

    public ClientDto(Client client) {
        this.utilisateurId = client.getUtilisateurId();
        this.nom = client.getNom();
        this.prenom = client.getPrenom();
        this.email = client.getEmail();
        this.motDePasse = client.getMotDePasse();
        this.paysDto = new PaysDto(client.getPays());
        this.dateHeureInscription = client.getDateHeureInscription();
    }

    public Long getUtilisateurId() { return this.utilisateurId; }

    public String getNom() { return this.nom; }

    public String getPrenom() { return this.prenom; }

    public String getEmail() { return this.email; }
    public String getMotDePasse() { return this.motDePasse; }

    public PaysDto getPays() { return this.paysDto; }

    public LocalDateTime getDateHeureInscription() { return this.dateHeureInscription; }

}
