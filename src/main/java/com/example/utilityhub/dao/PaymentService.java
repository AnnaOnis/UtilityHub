package com.example.utilityhub.dao;

import com.example.utilityhub.models.Payment;

import java.util.List;

public interface PaymentService extends DAO<Payment>{
    List<Payment> getPaymentsByUserId(Long userId);
}
