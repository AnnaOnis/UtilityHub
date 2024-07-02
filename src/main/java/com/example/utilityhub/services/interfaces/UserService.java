package com.example.utilityhub.services.interfaces;

import com.example.utilityhub.dao.DAO;
import com.example.utilityhub.models.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService extends DAO<User> {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    Page<User> getAllUsersPageable(Pageable pageable);
}
