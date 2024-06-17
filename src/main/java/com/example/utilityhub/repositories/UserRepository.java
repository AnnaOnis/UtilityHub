package com.example.utilityhub.repositories;

import com.example.utilityhub.models.Payment;
import com.example.utilityhub.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
