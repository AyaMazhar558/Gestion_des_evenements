package com.microservice_notification.microservice_notification.exception;

public class NotificationNotFound extends RuntimeException {
    public NotificationNotFound(String message) {
        super(message);
    }
}
