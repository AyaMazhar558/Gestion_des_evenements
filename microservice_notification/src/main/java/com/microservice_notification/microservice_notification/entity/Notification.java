package com.microservice_notification.microservice_notification.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNotification;

    @NotNull
    @Column(nullable = false)
    private String message;

    @NotNull
    @Column(nullable = false)
    private boolean dejavue;

    @NotNull
    @Column(nullable = false)
    private Long idUser; // Correction camelCase
}
