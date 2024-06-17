package com.example.utilityhub.repositories;

import com.example.utilityhub.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository  extends JpaRepository<Payment, Long> {
    List<Payment> findByUserId(Long userId);
}
