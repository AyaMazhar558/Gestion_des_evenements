package com.GestionUtilisateur.Microservice.Utilisateur.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String lastname;

    @Column(nullable = false, unique = true)
    private String telephone;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;
}