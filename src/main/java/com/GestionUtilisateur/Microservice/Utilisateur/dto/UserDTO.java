package com.GestionUtilisateur.Microservice.Utilisateur.dto;

import jakarta.validation.constraints.Email;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @NonNull
    private String username;

    @NonNull
    @Email
    private String email;

    @NonNull
    private String password;
}
