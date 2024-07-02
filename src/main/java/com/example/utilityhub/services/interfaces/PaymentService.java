package com.example.utilityhub.services.interfaces;

import com.example.utilityhub.dao.DAO;
import com.example.utilityhub.models.entities.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PaymentService extends DAO<Payment> {
    List<Payment> getPaymentsByUserId(Long userId);

    Page<Payment> getAllPaymentsPageable(Pageable pageable);
    Page<Payment> getPaymentsPageableByUserId(Long userId, Pageable pageable);
}
