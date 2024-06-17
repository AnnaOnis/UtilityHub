package com.example.utilityhub.dao;

import com.example.utilityhub.models.User;

import java.util.Optional;

public interface UserService extends DAO<User>{
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
