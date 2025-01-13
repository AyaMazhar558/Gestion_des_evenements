package com.microservice_notification.microservice_notification.repository;

import com.microservice_notification.microservice_notification.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository <Notification, Long> {

    List<Notification> findByIdUser(Long idUser);
}
