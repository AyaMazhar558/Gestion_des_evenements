package com.Microservice_Participation.Microservice_Participation.controller;

import com.Microservice_Participation.Microservice_Participation.dto.ParticipationDTO;
import com.Microservice_Participation.Microservice_Participation.entity.Participation;
import com.Microservice_Participation.Microservice_Participation.service.ParticipationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/partici")
public class ParticipationController {
    private final ParticipationService participationService;

    @PostMapping
    public ResponseEntity<Object> createParticipation(@RequestBody ParticipationDTO participationDTO) {
        try {
            Participation participation = participationService.createParticipation(participationDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(participation);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Participation>> getAllParticipations() {
        List<Participation> participations = participationService.getAllParticipation();
        return ResponseEntity.ok(participations);
    }
    @GetMapping("/en-attente")
    public ResponseEntity<List<Participation>> getParticipationsEnAttente() {
        List<Participation> participations = participationService.getParticipationsEnAttente();
        return ResponseEntity.ok(participations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getParticipationById(@PathVariable Long id) {
        try {
            Participation participation = participationService.getParticipationById(id);
            return ResponseEntity.ok(participation);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @GetMapping("/etud/{id}")
    public ResponseEntity<List<Participation>> getAllParticipationsByEtud(@PathVariable("id") Long id) {
        List<Participation> participations = participationService.getParticipationsByUserId(id);
        return ResponseEntity.ok(participations);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateParticipation(@PathVariable Long id, @RequestBody ParticipationDTO participationDTO) {
        try {
            Participation participation = participationService.updateParticipation(id, participationDTO);
            return ResponseEntity.ok(participation);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParticipation(@PathVariable Long id) {
        participationService.deleteParticipation(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}/accepter")
    public ResponseEntity<Object> accepterParticipation(@PathVariable Long id) {
        try {
            Participation participation = participationService.accepterParticipation(id);
            return ResponseEntity.ok(participation);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}/refuser")
    public ResponseEntity<Object> refuserParticipation(@PathVariable Long id) {
        try {
            Participation participation = participationService.refuserParticipation(id);
            return ResponseEntity.ok(participation);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
