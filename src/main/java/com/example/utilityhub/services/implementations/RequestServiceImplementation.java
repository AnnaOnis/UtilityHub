package com.example.utilityhub.services.implementations;

import com.example.utilityhub.services.interfaces.RequestService;
import com.example.utilityhub.models.entities.Request;
import com.example.utilityhub.models.repositories.RequestRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RequestServiceImplementation implements RequestService {

    private final RequestRepository requestRepository;

    @Override
    public Request save(Request request) {
        return requestRepository.save(request);
    }

    @Override
    public List<Request> getAll() {
        return requestRepository.findAll();
    }

    @Override
    public List<Request> getRequestsByUserId(Long userId) {
        return requestRepository.findByUserId(userId);
    }

    @Override
    public Page<Request> getAllRequestsPageable(Pageable pageable) {
        return requestRepository.findAll(pageable);
    }

    @Override
    public Page<Request> getRequestsPageableByUserId(Long userId, Pageable pageable) {
        return requestRepository.findRequestPageableByUserId(userId, pageable);
    }

    @Override
    public Optional<Request> findById(Long id) {
        return requestRepository.findById(id);
    }

    @Override
    public Request update(Request request) {
        if(!requestRepository.existsById(request.getId())){
            throw new EntityNotFoundException("Request not found with id " + request.getId());
        }
        return requestRepository.save(request);
    }

    @Override
    public boolean delete(Long id) {
        if(!requestRepository.existsById(id)){
            throw new EntityNotFoundException("Request not found with id " + id);
        }else {
            requestRepository.deleteById(id);
            return true;
        }
    }
}
