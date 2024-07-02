package com.example.utilityhub.models.repositories;

import com.example.utilityhub.models.entities.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository   extends JpaRepository<Notification, Long> {
    List<Notification> findByUserId(Long userId);
    Page<Notification> findNotificationsPageableByUserId(Long userId, Pageable pageable);
}
