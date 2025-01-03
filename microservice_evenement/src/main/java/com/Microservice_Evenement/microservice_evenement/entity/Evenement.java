package com.Microservice_Evenement.microservice_evenement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "evenement")
public class Evenement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEvenement;

    @NotNull
    @Column(nullable = false)
    private String titre;

    @NotNull
    @Column(nullable = false)
    private Date dateDebut;

    @NotNull
    @Column(nullable = false)
    private Date dateFin;

    @NotNull
    @Column(nullable = false)
    private String description;

    @NotNull
    @Column(nullable = false)
    private String role;

    @NotNull
    @Column(nullable = false)
    private String responsable;

    @NotNull
    @Column(nullable = false)
    private int nbrParticipant;

    @NotNull
    @Column(nullable = false)
    private boolean decision;

    @Column(nullable = true)
    private String causeRefus; // Optional field
}
