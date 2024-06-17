package com.example.utilityhub.services;

import com.example.utilityhub.dao.UserService;
import com.example.utilityhub.models.User;
import com.example.utilityhub.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public User save(User user) {
        if (userRepository.findByUsername(user.getUsername()).isEmpty() &&
                userRepository.findByEmail(user.getEmail()).isEmpty()){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        }

        return null;
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User update(User user) {
        if (!userRepository.existsById(user.getId())) {
            throw new EntityNotFoundException("User not found with id " + user.getId());
        }

        return userRepository.save(user);
    }

    @Override
    public boolean delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found with id " + id);
        }else {
            userRepository.deleteById(id);
            return true;
        }
    }
}
