package com.example.utilityhub.services.interfaces;

import com.example.utilityhub.dao.DAO;
import com.example.utilityhub.models.entities.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NotificationService extends DAO<Notification> {
    List<Notification> getNotificationsByUserId(Long userId);

    Page<Notification> getAllNotificationsPageable(Pageable pageable);
    Page<Notification> getNotificationsPageableByUserId(Long userId, Pageable pageable);
}
