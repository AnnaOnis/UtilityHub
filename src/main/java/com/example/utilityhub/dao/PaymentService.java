package com.example.utilityhub.dao;

import com.example.utilityhub.models.Payment;
import com.example.utilityhub.models.Request;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PaymentService extends DAO<Payment>{
    List<Payment> getPaymentsByUserId(Long userId);

    Page<Payment> getAllPaymentsPageable(Pageable pageable);
    Page<Payment> getPaymentsPageableByUserId(Long userId, Pageable pageable);
}
