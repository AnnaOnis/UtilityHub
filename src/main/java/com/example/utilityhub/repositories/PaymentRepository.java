package com.example.utilityhub.repositories;

import com.example.utilityhub.models.Payment;
import com.example.utilityhub.models.Request;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository  extends JpaRepository<Payment, Long> {
    List<Payment> findByUserId(Long userId);

    Page<Payment> findPaymentPageableByUserId(Long userId, Pageable pageable);
}
