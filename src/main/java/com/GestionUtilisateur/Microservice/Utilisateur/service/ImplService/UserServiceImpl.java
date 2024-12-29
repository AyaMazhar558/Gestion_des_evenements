package com.GestionUtilisateur.Microservice.Utilisateur.service.ImplService;

import com.GestionUtilisateur.Microservice.Utilisateur.dto.UserDTO;
import com.GestionUtilisateur.Microservice.Utilisateur.entity.User;
import com.GestionUtilisateur.Microservice.Utilisateur.repository.UserRepository;
import com.GestionUtilisateur.Microservice.Utilisateur.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User createUser(UserDTO userDTO) {
        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Un utilisateur avec cet email existe déjà");
        }
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, UserDTO userDTO) {
        return userRepository.findById(id).map(existingUser -> {
            // Mettez à jour les champs de l'utilisateur
            existingUser.setUsername(userDTO.getUsername());
            existingUser.setEmail(userDTO.getEmail());
            existingUser.setPassword(userDTO.getPassword());
            return userRepository.save(existingUser);
        }).orElseThrow(() -> new IllegalArgumentException("Utilisateur avec l'ID " + id + " non trouvé."));
    }
}