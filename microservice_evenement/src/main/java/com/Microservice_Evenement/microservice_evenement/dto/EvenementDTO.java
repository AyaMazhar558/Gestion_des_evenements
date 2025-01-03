package com.Microservice_Evenement.microservice_evenement.dto;

import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EvenementDTO {
    @NotNull
    private String titre;
    @NotNull
    private Date dateDebut;
    @NotNull
    private Date dateFin;
    @NotNull
    private String description;
    @NotNull
    private String role;
    @NotNull
    private String responsable;
    @NotNull
    private int nbrParticipant;
    @NotNull
    private boolean decision;
    private String causeRefus; // Optional if decision = true
}
