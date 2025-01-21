package com.GestionUtilisateur.Microservice.Utilisateur.controller;

import com.GestionUtilisateur.Microservice.Utilisateur.dto.UserDTO;
import com.GestionUtilisateur.Microservice.Utilisateur.entity.User;
import com.GestionUtilisateur.Microservice.Utilisateur.exception.UserNotFoundException;
import com.GestionUtilisateur.Microservice.Utilisateur.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody UserDTO userDTO) {
        try {
            User user = userService.createUser(userDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    @GetMapping("/responsables")
    public ResponseEntity<List<User>> getResponsables() {
        List<User> responsables = userService.getResponsables();
        return ResponseEntity.ok(responsables);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Utilisateur avec l'ID " + id + " non trouvé");
        }
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id,@RequestBody UserDTO userDTO) {
        User updatedUser = userService.updateUser(id, userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody Map<String, String> credentials, HttpSession session) {
        String email = credentials.get("email");
        String password = credentials.get("password");
        User user = userService.getUserByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            // Stocker l'ID utilisateur dans la session
            session.setAttribute("userId", user.getId());

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Connexion réussie");
            response.put("id", user.getId());
            response.put("role", user.getRoleUser());

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email ou mot de passe incorrect.");
        }
    }
}
