package com.example.utilityhub.models.repositories;

import com.example.utilityhub.models.entities.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository  extends JpaRepository<Payment, Long> {
    List<Payment> findByUserId(Long userId);

    Page<Payment> findPaymentPageableByUserId(Long userId, Pageable pageable);
}
