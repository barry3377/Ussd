package com.ussd.app.Ussd.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Travail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "jour_id", nullable = false,insertable = false)
    private Jour jours;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "heure_id", nullable = false)
    private Heure heures;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "departement_id", nullable = false)
    private Departement departement;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "hopital_id", nullable = false)
    private Hopital hopital;

    public Travail() {
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public Jour getJours() {
        return jours;
    }

    public void setJours(Jour jours) {
        this.jours = jours;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

    public Hopital getHopital() {
        return hopital;
    }

    public void setHopital(Hopital hopital) {

        this.hopital = hopital;
    }

    public Heure getHeures() {
        return heures;
    }

    public void setHeures(Heure heures) {
        this.heures = heures;
    }
}
