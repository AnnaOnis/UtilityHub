package com.example.utilityhub.repositories;

import com.example.utilityhub.models.Request;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestRepository  extends JpaRepository<Request, Long> {
    List<Request> findByUserId(Long userId);
}
