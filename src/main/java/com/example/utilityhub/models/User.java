package com.example.utilityhub.models;

import com.example.utilityhub.models.enums.Role;
import lombok.*;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    private String fullName;

    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false, updatable = false)
    private Timestamp createdAt;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", insertable=false, updatable=false)
    private Set<Payment> payments;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", insertable=false, updatable=false)
    private Set<Notification> notifications;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", insertable=false, updatable=false)
    private Set<Request> requests;

    @PrePersist
    protected void onCreate() {
        createdAt = new Timestamp(System.currentTimeMillis());
    }

    public User(String username, String password, String email){

        this.username = username;
        this.password = password;
        this.email = email;
        this.role = Role.USER;
        this.requests = new HashSet<>();
        this.notifications = new HashSet<>();
        this.payments = new HashSet<>();
    }

    public void addNotification(String message){
        Notification notification = new Notification();
        notification.setUser(this);
        notification.setMessage(message);
        notification.setIsRead(false);

        this.getNotifications().add(notification);
    }
}

