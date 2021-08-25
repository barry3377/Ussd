
package com.ussd.app.Ussd.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Jours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom_jour;
    @OneToMany(mappedBy = "jours", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RendezVous> items;
    public Jours() {
    }

    public Jours(String nom_jour) {
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

    public List<RendezVous> getItems() {
        return items;
    }

    public void setItems(List<RendezVous> items) {
        this.items = items;
    }


}
