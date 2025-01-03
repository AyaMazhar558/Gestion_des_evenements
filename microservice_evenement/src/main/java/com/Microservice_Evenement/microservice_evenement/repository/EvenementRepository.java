package com.Microservice_Evenement.microservice_evenement.repository;

import com.Microservice_Evenement.microservice_evenement.entity.Evenement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EvenementRepository extends JpaRepository<Evenement, Long> {

}
