package com.microservice_notification.microservice_notification.repository;

import com.microservice_notification.microservice_notification.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository <Notification, Long> {
}
