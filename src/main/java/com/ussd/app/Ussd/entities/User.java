package com.ussd.app.Ussd.entities;

import com.ussd.app.Ussd.Dto.FieldMatch;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match")
@Entity

@Table(name="user", uniqueConstraints = @UniqueConstraint(columnNames ="email" ))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String nom;
    private  String prenom;
    private String email;
    private  String  password;
    private  String  confirmPassword;


    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    @JoinTable(name = "users_roles",joinColumns=@JoinColumn(name = "users_id",referencedColumnName = "id"),
            inverseJoinColumns =@JoinColumn(name ="role_id",referencedColumnName = "id") )
    private Set<Role> roles = new HashSet<>();

    public User(String nom, String nom1, String email, String password) {
    }

    public <T> User(String nom, String nom1, String email, String password, List<T> role_user) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public User() {
    }

    public User(String nom, String prenom, String email, String password, Set<Role> roles) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
}
