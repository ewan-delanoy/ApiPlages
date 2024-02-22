package com.ewan.apiplages.entity;



import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE_UTILISATEUR")
public abstract class Utilisateur  {

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


    public Long getUtilisateurId() {
        return utilisateurId;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

}
