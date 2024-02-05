package com.ewan.apiplages.entity;



import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.Collection;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE_UTILISATEUR")
public abstract class Utilisateur implements UserDetails {

    @Id
    // @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "client_sequence")
    // @SequenceGenerator(name="client_sequence", initialValue = 1000)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    protected Long utilisateurId;

    @Column(length=80)
    protected String nom;

    protected String prenom;

    @Column(length=150)
    protected String email;


    protected String motDePasse;

    // Le no-args constructor demandé par JPA
    protected Utilisateur() {
        super();
    }
    public Utilisateur(String nom,String prenom,String email,String motDePasse) {
        super();
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
    }
    public String getNomEtPrenom() {
        return nom.toUpperCase() + " " + prenom;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }


    public String getPassword() {
        return motDePasse;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
