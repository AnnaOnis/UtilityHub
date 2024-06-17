package com.example.utilityhub.services;

import com.example.utilityhub.dao.PaymentService;
import com.example.utilityhub.models.Payment;
import com.example.utilityhub.repositories.PaymentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentServiceImplementation implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public List<Payment> getAll() {
        return paymentRepository.findAll();
    }

    @Override
    public Optional<Payment> findById(Long id) {
        return paymentRepository.findById(id);
    }

    @Override
    public List<Payment> getPaymentsByUserId(Long userId) {
        return paymentRepository.findByUserId(userId);
    }

    @Override
    public Payment update(Payment payment) {
        if (!paymentRepository.existsById(payment.getId())) {
            throw new EntityNotFoundException("Payment not found with id " + payment.getId());
        }
        return paymentRepository.save(payment);
    }

    @Override
    public boolean delete(Long id) {
        if (!paymentRepository.existsById(id)) {
            throw new EntityNotFoundException("Payment not found with id " + id);
        }else{
            paymentRepository.deleteById(id);
            return true;
        }
    }
}
