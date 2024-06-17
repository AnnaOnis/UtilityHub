package com.example.utilityhub.repositories;

import com.example.utilityhub.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository   extends JpaRepository<Notification, Long> {
    List<Notification> findByUserId(Long userId);
}
