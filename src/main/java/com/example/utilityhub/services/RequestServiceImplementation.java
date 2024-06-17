package com.example.utilityhub.services;

import com.example.utilityhub.dao.RequestService;
import com.example.utilityhub.models.Request;
import com.example.utilityhub.repositories.RequestRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
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
