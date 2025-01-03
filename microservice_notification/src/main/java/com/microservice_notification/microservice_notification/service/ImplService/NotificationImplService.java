package com.microservice_notification.microservice_notification.service.ImplService;

import com.microservice_notification.microservice_notification.dto.NotificationDTO;
import com.microservice_notification.microservice_notification.entity.Notification;
import com.microservice_notification.microservice_notification.repository.NotificationRepository;
import com.microservice_notification.microservice_notification.service.NotificationService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationImplService implements NotificationService {

    private final NotificationRepository notificationRepository;

    @Override
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    @Override
    public Notification getNotificationById(Long id) {
        return notificationRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Notification avec l'ID " + id + " non trouvée."));
    }

    @Override
    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }

    @Override
    public Notification createNotification(NotificationDTO notificationDTO) {
        Notification notification = new Notification();
        notification.setMessage(notificationDTO.getMessage());
        notification.setDejavue(notificationDTO.isDejavue());
        notification.setIdUser(notificationDTO.getIdUser());

        return notificationRepository.save(notification);
    }

    @Override
    public Notification updateNotification(Long id, NotificationDTO notificationDTO) {
        return notificationRepository.findById(id).map(existingNotification -> {
            existingNotification.setMessage(notificationDTO.getMessage());
            existingNotification.setDejavue(notificationDTO.isDejavue());
            existingNotification.setIdUser(notificationDTO.getIdUser());

            return notificationRepository.save(existingNotification);
        }).orElseThrow(() -> new IllegalArgumentException("Notification avec l'ID " + id + " non trouvée."));
    }
}
