package com.GestionUtilisateur.Microservice.Utilisateur.service;

import com.GestionUtilisateur.Microservice.Utilisateur.dto.UserDTO;
import com.GestionUtilisateur.Microservice.Utilisateur.entity.User;
import com.GestionUtilisateur.Microservice.Utilisateur.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();
    public User getUserById(Long id);
    public void deleteUser(Long id);
    public User createUser(UserDTO userDTO);
    public User updateUser(Long id,UserDTO userDTO);

    User getUserByEmail(String email);
    public List<User> getResponsables();

    }