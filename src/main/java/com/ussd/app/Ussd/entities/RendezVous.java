package com.ussd.app.Ussd.entities;

import javax.persistence.*;
import java.util.Optional;

@Entity
public class RendezVous {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String  Ticket;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "jour_id", nullable = false)
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
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userTransaction", nullable = false)
    private UserTransaction userTransaction;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTicket() {
        return Ticket;
    }

    public void setTicket(String ticket) {
        Ticket = ticket;
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

    public UserTransaction getUserTransaction() {
        return userTransaction;
    }

    public void setUserTransaction(UserTransaction userTransaction) {
        this.userTransaction = userTransaction;
    }

    public RendezVous() {
    }
}
