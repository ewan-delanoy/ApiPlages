package com.ewan.apiplages.entity;

import java.util.List;

import com.ewan.apiplages.dto.PaysDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
// import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity // Annotation de JPA pour demander la création d'une table pays
public class Pays {

    @Id
    @Column(length=2)
    private String code;

    // @NonNull
    // @Size(min=2, message="Le nom du caractère doit comporter au moins {min} caractères")
    private String nom;


    @OneToMany(mappedBy="pays", fetch=FetchType.EAGER, cascade = CascadeType.REMOVE)
    // @ToString.Exclude
    @JsonIgnore
    private List<Client> clients;

    // No-args constructor demandé par JPA
    protected Pays() {
        super();
    }
    public Pays(String code,String nom) {
        this.code = code ;
        this.nom = nom;
    }

    public Pays(PaysDto paysDto) {this(paysDto.getCode(),paysDto.getNom());}

    public String getCode() { return this.code; }
    public String getNom() { return this.nom; }
}