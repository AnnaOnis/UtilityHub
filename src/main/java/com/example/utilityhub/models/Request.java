package com.example.utilityhub.models;

import com.example.utilityhub.models.enums.RequestStatus;
import com.example.utilityhub.models.enums.RequestType;
import lombok.*;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "requests")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RequestType type;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RequestStatus status;

    @Column(nullable = false, updatable = false)
    private Timestamp createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Timestamp(System.currentTimeMillis());
    }

    public Request(String title, String description, String type){
        this.title = title;
        this.description = description;
        this.status = RequestStatus.PENDING;
        try {
            this.type = RequestType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            this.type = RequestType.OTHER;
        }
    }
}

