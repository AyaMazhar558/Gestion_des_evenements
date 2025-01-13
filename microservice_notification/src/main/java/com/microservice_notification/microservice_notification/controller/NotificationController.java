package com.microservice_notification.microservice_notification.controller;

import com.microservice_notification.microservice_notification.dto.NotificationDTO;
import com.microservice_notification.microservice_notification.entity.Notification;
import com.microservice_notification.microservice_notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notif")
public class NotificationController {
    private final NotificationService notificationService;

    @PostMapping
    public ResponseEntity<Object> createNotification(@RequestBody NotificationDTO notificationDTO) {
        try {
            Notification notification = notificationService.createNotification(notificationDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(notification);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Notification>> getAllNotifications() {
        List<Notification> notifications = notificationService.getAllNotifications();
        return ResponseEntity.ok(notifications);
    }
    @GetMapping("/user/{idUser}")
    public ResponseEntity<List<Notification>> getNotificationsByUserId(@PathVariable Long idUser) {
        List<Notification> notifications = notificationService.getNotificationsByUserId(idUser);
        return ResponseEntity.ok(notifications);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getNotificationById(@PathVariable Long id) {
        try {
            Notification notification = notificationService.getNotificationById(id);
            return ResponseEntity.ok(notification);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateNotification(@PathVariable Long id, @RequestBody NotificationDTO notificationDTO) {
        try {
            Notification notification = notificationService.updateNotification(id, notificationDTO);
            return ResponseEntity.ok(notification);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long id) {
        notificationService.deleteNotification(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
