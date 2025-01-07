package com.Microservice_Participation.Microservice_Participation.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "participation")
public class Participation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idParticipation;

    @NotNull
    @Column(nullable = false)
    private Long idEvenement;

    @NotNull
    @Column(nullable = false)
    private Long idUser;
    @NotNull
    @Column(nullable = false)
    private String acceptEtud;
}