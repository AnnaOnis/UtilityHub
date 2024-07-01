package com.example.utilityhub.dao;

import com.example.utilityhub.models.Request;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RequestService extends DAO<Request>{
    List<Request> getRequestsByUserId(Long id);

    Page<Request> getAllRequestsPageable(Pageable pageable);

    Page<Request> getRequestsPageableByUserId(Long userId, Pageable pageable);
}
