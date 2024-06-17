package com.example.utilityhub.services;

import com.example.utilityhub.dao.UtilityServiceService;
import com.example.utilityhub.models.UtilityService;
import com.example.utilityhub.repositories.UtilityServiceRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UtilityServiceServiceImplementation implements UtilityServiceService {

    private final UtilityServiceRepository utilityServiceRepository;

    @Override
    public UtilityService save(UtilityService utilityService) {

        return utilityServiceRepository.save(utilityService);
    }

    @Override
    public List<UtilityService> getAll() {

        return utilityServiceRepository.findAll();
    }

    @Override
    public Optional<UtilityService> findById(Long id) {

        return utilityServiceRepository.findById(id);
    }

    @Override
    public UtilityService update(UtilityService utilityService) {
        if (!utilityServiceRepository.existsById(utilityService.getId())) {
            throw new EntityNotFoundException("UtilityService not found with id " + utilityService.getId());
        }
        return utilityServiceRepository.save(utilityService);
    }

    @Override
    public boolean delete(Long id) {
        if (!utilityServiceRepository.existsById(id)) {
            throw new EntityNotFoundException("UtilityService not found with id " + id);
        }else {
            utilityServiceRepository.deleteById(id);
            return true;
        }
    }
}
