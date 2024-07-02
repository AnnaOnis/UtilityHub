package com.example.utilityhub.models.entities;

import com.example.utilityhub.models.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private User user;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private UtilityService service;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private LocalDate paymentDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status;

    @Column(nullable = false, updatable = false)
    private Timestamp createdAt;

    public Payment(LocalDate paymentDate, Double amount, UtilityService utilityService, User user){
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.service = utilityService;
        this.user = user;
        this.status = PaymentStatus.PENDING;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = new Timestamp(System.currentTimeMillis());
    }
}

