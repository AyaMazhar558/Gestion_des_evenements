package com.Microservice_Participation.Microservice_Participation.repository;

import com.Microservice_Participation.Microservice_Participation.entity.Participation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipationRepository extends JpaRepository<Participation, Long> {

    List<Participation> findByIdUser(Long idUser);


}
