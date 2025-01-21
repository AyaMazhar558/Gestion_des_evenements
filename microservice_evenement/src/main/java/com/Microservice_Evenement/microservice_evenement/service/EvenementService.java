package com.Microservice_Evenement.microservice_evenement.service;

import com.Microservice_Evenement.microservice_evenement.dto.EvenementDTO;
import com.Microservice_Evenement.microservice_evenement.entity.Evenement;

import java.util.List;

public interface EvenementService {
    List<Evenement> getAllEvenement();
    Evenement getEvenementById(Long id);
    void deleteEvenement(Long id);
    Evenement createEvenement(EvenementDTO evenementDTO);
    Evenement updateEvenement(Long id, EvenementDTO evenementDTO);
    List<Evenement> getEvenementsByResponsable (Long responsableId);

    Evenement stateEvenement(Long id);
    Evenement updateDecisionAndCauseRefus(Long id, String causeRefus);
    Evenement updateEvenementFields(Long id, EvenementDTO evenementDTO);


}
