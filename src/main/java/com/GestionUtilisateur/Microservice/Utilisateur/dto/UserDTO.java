package com.GestionUtilisateur.Microservice.Utilisateur.dto;
import lombok.*;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @NonNull
    private String username;
    @NonNull
    private String lastname;
    @NonNull
    private String telephone;
    @NonNull
    private String email;
    @NonNull
    private String password;
    @NonNull
    private String roleUser;
}
