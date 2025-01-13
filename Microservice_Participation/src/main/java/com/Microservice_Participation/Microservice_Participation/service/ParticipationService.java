package com.Microservice_Participation.Microservice_Participation.service;

import com.Microservice_Participation.Microservice_Participation.dto.ParticipationDTO;
import com.Microservice_Participation.Microservice_Participation.entity.Participation;

import java.util.List;

public interface ParticipationService {
    List<Participation> getAllParticipation();
    Participation getParticipationById(Long id);
    void deleteParticipation(Long id);
    Participation createParticipation(ParticipationDTO participationDTO);
    Participation updateParticipation(Long id, ParticipationDTO participationDTO);
    List<Participation> getParticipationsByUserId(Long idUser);

}

