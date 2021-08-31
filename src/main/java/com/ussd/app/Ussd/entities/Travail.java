package com.ussd.app.Ussd.entities;

import javax.persistence.*;

@Entity
@Table(name = "rendezvous")
public class Travail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "jour_id", nullable = false)
    private Jour jours;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Heure heures;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "departement_id", nullable = false)

    private Departement departement;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "hopital_id", nullable = false)
    private Hopital hopital;
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "userTrans_id", nullable = false)
//    private  UserTransaction userTransaction;


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

    public Heure getHeures() {
        return heures;
    }

    public void setHeures(Heure heures) {
        this.heures = heures;
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

//    public UserTransaction getUserTransaction() {
//        return userTransaction;
//    }
//
//    public void setUserTransaction(UserTransaction userTransaction) {
//        this.userTransaction = userTransaction;
//    }
}
