package com.Microservice_Participation.Microservice_Participation.dto;

import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Data
public class ParticipationDTO {
    @NotNull
    private Long idEvenement;
    @NotNull
    private Long idUser;
    @NotNull
    private String acceptEtud;
}
