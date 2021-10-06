package com.ussd.app.Ussd.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
public class UserTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String msisdn;
    @OneToMany(mappedBy = "userTransaction", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RendezVous> items;
    private LocalDateTime createdAt;


    public UserTransaction() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public String toString() {
        return this.msisdn;
    }
}
