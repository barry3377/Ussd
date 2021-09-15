package com.ussd.app.Ussd.entities;


import lombok.Data;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.List;



@Entity

@SQLDelete(sql = "UPDATE heure SET deleted=true WHERE id=?")
@Where(clause = "deleted=false")
public class Heure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private String interval_heur;
    private Long numero;
    private boolean deleted=Boolean.FALSE;
//    @ManyToMany(mappedBy="heures")
//    private Set<Travail> travails = new HashSet<>();
@OneToMany(mappedBy = "heures", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
private List<Travail> items;
    @OneToMany(mappedBy = "heures", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RendezVous> item;
    public Heure() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getInterval_heur() {
        return interval_heur;
    }

    public void setInterval_heur(String interval_heur) {
        this.interval_heur = interval_heur;
    }

    public Heure(String interval_heur) {
        this.interval_heur = interval_heur;
    }

    @Override
    public String toString() {
        return this.interval_heur ;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public List<Travail> getItems() {
        return items;
    }

    public void setItems(List<Travail> items) {
        this.items = items;
    }
}

