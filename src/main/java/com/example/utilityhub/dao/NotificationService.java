package com.example.utilityhub.dao;

import com.example.utilityhub.models.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NotificationService extends DAO<Notification>{
    List<Notification> getNotificationsByUserId(Long userId);

    Page<Notification> getAllNotificationsPageable(Pageable pageable);
}
