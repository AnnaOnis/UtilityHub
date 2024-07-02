package com.example.utilityhub.services.implementations;

import com.example.utilityhub.services.interfaces.UtilityServiceService;
import com.example.utilityhub.models.entities.UtilityService;
import com.example.utilityhub.models.repositories.UtilityServiceRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        if (utilityServiceRepository.existsById(id)) {
            utilityServiceRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Page<UtilityService> getAllUtilityServicesPageable(Pageable pageable) {
        return utilityServiceRepository.findAll(pageable);
    }
}
