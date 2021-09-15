
package com.ussd.app.Ussd.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Jour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom_jour;
    @OneToMany(mappedBy = "jours", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Travail> items;
    @OneToMany(mappedBy = "jours", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RendezVous> item;
    public Jour() {
    }

    public Jour(String nom_jour) {
        this.nom_jour = nom_jour;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom_jour() {
        return nom_jour;
    }

    public void setNom_jour(String nom_jour) {
        this.nom_jour = nom_jour;
    }

    public List<Travail> getItems() {
        return items;
    }

    public void setItems(List<Travail> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return this.nom_jour ;
    }
}
