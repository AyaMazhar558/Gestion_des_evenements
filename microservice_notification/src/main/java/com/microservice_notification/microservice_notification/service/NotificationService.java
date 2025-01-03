package com.microservice_notification.microservice_notification.service;

import com.microservice_notification.microservice_notification.dto.NotificationDTO;

import com.microservice_notification.microservice_notification.entity.Notification;// Correct

import java.util.List;

public interface NotificationService {
    List<Notification> getAllNotifications();
    Notification getNotificationById(Long id);
    void deleteNotification(Long id);
    Notification createNotification(NotificationDTO notificationDTO);
    Notification updateNotification(Long id, NotificationDTO notificationDTO);
}
