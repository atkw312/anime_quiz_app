package com.tkwang312.auth.model;

import jakarta.persistence.*;

@Entity
@Table(name="users")
public class User {

    // generate an id, string and username need getters and setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String passwordHash;

    public void setUsername(String usr){
        this.username = usr;
    }
    public void setPasswordHash(String pwd){
        this.passwordHash = pwd;
    }

    public String getUsername(){
        return this.username;
    }
    public String getPasswordHash(){
        return this.passwordHash;
    }

    public Long getId() {
        return this.id;
    }
}
