package com.GestionUtilisateur.Microservice.Utilisateur.dto;

import jakarta.validation.constraints.Email;
import lombok.NonNull;


public class UserDTO {
    @NonNull

    private String username;
    @NonNull
    @Email

    private String email;
    @NonNull

    private String password;

    // Getters et setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
}
