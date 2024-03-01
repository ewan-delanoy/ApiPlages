package com.ewan.apiplages.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Statut {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long statutId;


    private String nom;

    // No-args constructor demandé par JPA
    protected Statut() {
        super();
    }
    public Statut(String nom) {
        super() ;
        this.nom = nom;
    }

    public String getNom() { return this.nom; }
}	
