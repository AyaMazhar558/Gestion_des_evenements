package com.Microservice_Evenement.microservice_evenement.service.ImplService;

import com.Microservice_Evenement.microservice_evenement.dto.EvenementDTO;
import com.Microservice_Evenement.microservice_evenement.entity.Evenement;
import com.Microservice_Evenement.microservice_evenement.repository.EvenementRepository;
import com.Microservice_Evenement.microservice_evenement.service.EvenementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.ZoneId;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@Service
@RequiredArgsConstructor
public class EvenementServiceImpl implements EvenementService {

    private final EvenementRepository evenementRepository;

    @Override
    public List<Evenement> getAllEvenement() {
        List<Evenement> evenements = evenementRepository.findAll();

        // Filtrer les événements dont la date de fin est supérieure à la date actuelle
        LocalDate today = LocalDate.now();
        evenements.removeIf(evenement -> isDateExpired(evenement.getDateFin(), today));

        // Vérifier si la date de fin est expirée pour chaque événement
        evenements.forEach(evenement -> {
            if (isDateExpired(evenement.getDateFin(), today)) {
                evenement.setDescription(evenement.getDescription() + " (Cet événement a expiré.)");
            }
        });

        return evenements;
    }

    @Override
    public Evenement getEvenementById(Long id) {
        Evenement evenement = evenementRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Evenement avec l'ID " + id + " non trouvé.")
        );

        // Définir today dans la méthode
        LocalDate today = LocalDate.now();

        // Vérifier si la date de fin est expirée
        if (isDateExpired(evenement.getDateFin(), today)) {
            throw new IllegalArgumentException("L'événement avec l'ID " + id + " a expiré.");
        }

        return evenement;
    }
    @Override
    public List<Evenement> getEvenementsByResponsable(Long responsableId) {
        List<Evenement> evenements = evenementRepository.findByResponsable(responsableId);

        // If no events found for the given responsable ID, throw an exception
        if (evenements.isEmpty()) {
            throw new IllegalArgumentException("Aucun événement trouvé pour le responsable avec l'ID " + responsableId);
        }

        // Vérifier si la date de fin est expirée pour chaque événement
        LocalDate today = LocalDate.now();
        evenements.forEach(evenement -> {
            if (isDateExpired(evenement.getDateFin(), today)) {
                evenement.setDescription(evenement.getDescription() + " (Cet événement a expiré.)");
            }
        });

        return evenements;
    }

    @Override
    public Evenement stateEvenement(Long id, Boolean state) {
        Evenement evenement = evenementRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Evenement avec l'ID " + id + " non trouvé.")
        );
        if(state){
            evenement.setDecision("Accepter");
        }else{
            evenement.setDecision("Rejecter");
        }
        return evenementRepository.save(evenement);
    }


    @Override
    public void deleteEvenement(Long id) {
        evenementRepository.deleteById(id);
    }

    @Override
    public Evenement createEvenement(EvenementDTO evenementDTO) {
        validateDate(evenementDTO.getDateDebut().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        validateDate(evenementDTO.getDateFin().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        Evenement evenement = new Evenement();
        evenement.setTitre(evenementDTO.getTitre());
        evenement.setDateDebut(evenementDTO.getDateDebut());
        evenement.setDateFin(evenementDTO.getDateFin());
        evenement.setDescription(evenementDTO.getDescription());
        evenement.setLieu(evenementDTO.getLieu());
        evenement.setNbrParticipant(evenementDTO.getNbrParticipant());
        evenement.setRole(evenementDTO.getRole());
        evenement.setResponsable(evenementDTO.getResponsable());
        evenement.setDecision(evenementDTO.getDecision());
        evenement.setCauseRefus(evenementDTO.getCauseRefus());

        return evenementRepository.save(evenement);
    }

    @Override
    public Evenement updateEvenement(Long id, EvenementDTO evenementDTO) {
        validateDate(evenementDTO.getDateDebut().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        validateDate(evenementDTO.getDateFin().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        return evenementRepository.findById(id).map(existingEvenement -> {
            existingEvenement.setTitre(evenementDTO.getTitre());
            existingEvenement.setDateDebut(evenementDTO.getDateDebut());
            existingEvenement.setDateFin(evenementDTO.getDateFin());
            existingEvenement.setDescription(evenementDTO.getDescription());
            existingEvenement.setLieu(evenementDTO.getLieu());
            existingEvenement.setNbrParticipant(evenementDTO.getNbrParticipant());
            existingEvenement.setRole(evenementDTO.getRole());
            existingEvenement.setResponsable(evenementDTO.getResponsable());
            existingEvenement.setDecision(evenementDTO.getDecision());
            existingEvenement.setCauseRefus(evenementDTO.getCauseRefus());
            return evenementRepository.save(existingEvenement);
        }).orElseThrow(() -> new IllegalArgumentException("Evenement avec l'ID " + id + " non trouvé."));
    }
    /**
     * Méthode pour valider une date.
     * @param date La date à valider.
     */
    private void validateDate(LocalDate date) {
        if (date.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La date de l'événement ne peut pas être dans le passé.");
        }
    }

    /**
     * Méthode pour vérifier si une date est expirée.
     *
     * @param date  La date à vérifier.
     * @param today
     * @return true si la date est expirée, sinon false.
     */
    private boolean isDateExpired(Date date, LocalDate today) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate.isBefore(LocalDate.now());
    }

}
