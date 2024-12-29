package com.GestionUtilisateur.Microservice.Utilisateur.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message) {
        super(message);
    }
}