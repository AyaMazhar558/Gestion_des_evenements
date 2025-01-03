package com.Microservice_Evenement.microservice_evenement.controller;

import com.Microservice_Evenement.microservice_evenement.dto.EvenementDTO;
import com.Microservice_Evenement.microservice_evenement.entity.Evenement;
import com.Microservice_Evenement.microservice_evenement.service.EvenementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/evene")
public class EvenementController {
    private final EvenementService evenementService;
    @PostMapping
    public ResponseEntity<Object> createEvenement( @RequestBody EvenementDTO evenementDTO) {
        try {
            Evenement evenement = evenementService.createEvenement(evenementDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(evenement);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Evenement>> getAllUsers() {
        List<Evenement> evenement = evenementService.getAllEvenement();
        return ResponseEntity.ok(evenement);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getEvenementById(@PathVariable Long id) {
        Evenement evenement = evenementService.getEvenementById(id);
        if (evenement == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("evenement avec l'ID " + id + " non trouvé");
        }
        return ResponseEntity.ok(evenement);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Evenement> updateEvenement(@PathVariable Long id, @RequestBody EvenementDTO evenementDTO) {
        Evenement updateevenement = evenementService.updateEvenement(id, evenementDTO);
        return ResponseEntity.ok(updateevenement);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        evenementService.deleteEvenement(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
