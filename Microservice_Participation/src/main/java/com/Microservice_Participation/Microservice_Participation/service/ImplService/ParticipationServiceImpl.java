package com.Microservice_Participation.Microservice_Participation.service.ImplService;
import com.Microservice_Participation.Microservice_Participation.dto.ParticipationDTO;
import com.Microservice_Participation.Microservice_Participation.entity.Participation;
import com.Microservice_Participation.Microservice_Participation.repository.ParticipationRepository;
import com.Microservice_Participation.Microservice_Participation.service.ParticipationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.ZoneId;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@Service
@RequiredArgsConstructor
public class ParticipationServiceImpl implements ParticipationService {

    private final ParticipationRepository participationRepository;

    @Override
    public List<Participation> getAllParticipation() {
        List<Participation> participations = participationRepository.findAll();
        return participations;
    }

    @Override
    public Participation getParticipationById(Long id) {
        return participationRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Participation avec l'ID " + id + " non trouvée."));
    }
    @Override
    public List<Participation> getParticipationsByUserId(Long idUser) {
        return participationRepository.findByIdUser(idUser);
    }
    @Override
    public List<Participation> getParticipationsEnAttente() {
        return participationRepository.findByAcceptEtud("en attente");
    }
    @Override
    public void deleteParticipation(Long id) {
        participationRepository.deleteById(id);
    }

    @Override
    public Participation createParticipation(ParticipationDTO participationDTO) {
        Participation participation = new Participation();
        participation.setIdEvenement(participationDTO.getIdEvenement());
        participation.setIdUser(participationDTO.getIdUser());
        participation.setAcceptEtud(participationDTO.getAcceptEtud());
        return participationRepository.save(participation);
    }

    @Override
    public Participation updateParticipation(Long id, ParticipationDTO participationDTO) {

        return participationRepository.findById(id).map(existingParticipation -> {
            existingParticipation.setIdEvenement(participationDTO.getIdEvenement());
            existingParticipation.setIdUser(participationDTO.getIdUser());
            existingParticipation.setAcceptEtud(participationDTO.getAcceptEtud());
            return participationRepository.save(existingParticipation);
        }).orElseThrow(() -> new IllegalArgumentException("Participation avec l'ID " + id + " non trouvée."));
    }

    /**
     * Méthode pour valider une date.
     * @param date La date à valider.
     */
    private void validateDate(LocalDate date) {
        if (date.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La date de participation ne peut pas être dans le passé.");
        }
    }

    /**
     * Méthode pour vérifier si une date est expirée.
     * @param date La date à vérifier.
     * @return true si la date est expirée, sinon false.
     */
    private boolean isDateExpired(Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate.isBefore(LocalDate.now());
    }
}
