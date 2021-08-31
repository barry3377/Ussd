package com.ussd.app.Ussd.entities;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@SQLDelete(sql = "UPDATE hopital SET deleted=true WHERE hopital_id =?")
@Where(clause = "deleted=false")
public class Hopital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hopital_id")
    private Long id;
    @NotNull
    private String nom_hopital;
    private Long numero;
    private boolean deleted=Boolean.FALSE;
    @ManyToMany( cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "hopital_service",
            joinColumns = @JoinColumn(name = "hopital_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id"))
    private Set<Departement> departements = new HashSet<>();
   /* @ManyToMany
    private Collection<Departement> departements ;*/
    @OneToMany(mappedBy = "hopital", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Travail> items;


    public List<Travail> getItems() {
        return items;
    }

    public void setItems(List<Travail> items) {
        this.items = items;
    }

    public Hopital() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom_hopital() {
        return nom_hopital;
    }

    public void setNom_hopital(String nom_hopital) {
        this.nom_hopital = nom_hopital;
    }

    public Hopital(String nom_hopital) {
        this.nom_hopital = nom_hopital;
    }

    public Set<Departement> getDepartements() {
        return departements;
    }

    public void setDepartements(Set<Departement> departements) {
        this.departements = departements;
    }

    public Hopital(Set<Departement> departements) {
        this.departements = departements;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }
}



