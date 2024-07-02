package com.example.utilityhub.services.implementations;

import com.example.utilityhub.services.interfaces.PaymentService;
import com.example.utilityhub.models.entities.Payment;
import com.example.utilityhub.models.repositories.PaymentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<Payment> getAllPaymentsPageable(Pageable pageable) {
        return paymentRepository.findAll(pageable);
    }

    @Override
    public Page<Payment> getPaymentsPageableByUserId(Long userId, Pageable pageable) {
        return paymentRepository.findPaymentPageableByUserId(userId, pageable);
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
