package com.ussd.app.Ussd.entities;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@SQLDelete(sql = "UPDATE departement SET deleted=true WHERE departement_id=?")
@Where(clause = "deleted=false")
public class Departement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="departement_id")
    private Long id;
    private  String nom_service;
    private  Boolean deleted=Boolean.FALSE;

    @ManyToMany(mappedBy="departements")
    private Set<Hopital> hopitals = new HashSet<>();

    @OneToMany(mappedBy = "departement", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Travail> items;
    @OneToMany(mappedBy = "departement", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RendezVous> item;
    public Departement() {
    }

    public Departement(String nom_service) {
        this.nom_service = nom_service;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom_service() {
        return nom_service;
    }

    public void setNom_service(String nom_service) {
        this.nom_service = nom_service;
    }



    @Override
    public String toString() {
        return this.nom_service;
    }

    public Set<Hopital> getHopitals() {
        return hopitals;
    }

    public void setHopitals(Set<Hopital> hopitals) {
        this.hopitals = hopitals;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

}
