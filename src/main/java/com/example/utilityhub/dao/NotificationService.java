package com.example.utilityhub.dao;

import com.example.utilityhub.models.Notification;

import java.util.List;

public interface NotificationService extends DAO<Notification>{
    List<Notification> getNotificationsByUserId(Long userId);
}
