package com.example.utilityhub.models.repositories;

import com.example.utilityhub.models.entities.Request;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestRepository  extends JpaRepository<Request, Long> {
    List<Request> findByUserId(Long userId);

    Page<Request> findRequestPageableByUserId(Long userId, Pageable pageable);
}
