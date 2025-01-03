package com.Microservice_Evenement.microservice_evenement.exception;

public class EvenementNotFoundException extends RuntimeException {
    public EvenementNotFoundException(String message) {
        super(message);
    }
}
