package com.ussd.app.Ussd.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
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
        return "Heure{" +
                "id=" + id +
                ", interval_heur='" + interval_heur + '\'' +
                '}';
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
}

