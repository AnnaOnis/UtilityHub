package com.example.utilityhub.services;

import com.example.utilityhub.dao.NotificationService;
import com.example.utilityhub.models.Notification;
import com.example.utilityhub.repositories.NotificationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotificationServiceImplementation implements NotificationService {

    private  final NotificationRepository notificationRepository;

    @Override
    public Notification save(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public List<Notification> getAll() {
        return notificationRepository.findAll();
    }

    @Override
    public Optional<Notification> findById(Long id) {
        return notificationRepository.findById(id);
    }

    @Override
    public List<Notification> getNotificationsByUserId(Long userId) {
        return notificationRepository.findByUserId(userId);
    }

    @Override
    public Page<Notification> getAllNotificationsPageable(Pageable pageable) {
        return notificationRepository.findAll(pageable);
    }

    @Override
    public Notification update(Notification notification) {
        if (!notificationRepository.existsById(notification.getId())) {
            throw new EntityNotFoundException("Notification not found with id " + notification.getId());
        }
        return notificationRepository.save(notification);
    }

    @Override
    public boolean delete(Long id) {
        if (!notificationRepository.existsById(id)) {
            throw new EntityNotFoundException("Notification not found with id " + id);
        }else{
            notificationRepository.deleteById(id);
            return true;
        }
    }
}
