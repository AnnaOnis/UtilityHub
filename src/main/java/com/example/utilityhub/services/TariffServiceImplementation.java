package com.example.utilityhub.services;

import com.example.utilityhub.dao.TariffService;
import com.example.utilityhub.models.Tariff;
import com.example.utilityhub.repositories.TariffRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TariffServiceImplementation implements TariffService {

    private final TariffRepository tariffRepository;

    @Override
    public Tariff save(Tariff tariff) {
        return tariffRepository.save(tariff);
    }

    @Override
    public List<Tariff> getAll() {
        return tariffRepository.findAll();
    }

    @Override
    public Optional<Tariff> findById(Long id) {
        return tariffRepository.findById(id);
    }

    @Override
    public Tariff update(Tariff tariff) {
        if(!tariffRepository.existsById(tariff.getId())){
            throw new EntityNotFoundException("Tariff not found with id " + tariff.getId());
        }
        return tariffRepository.save(tariff);
    }

    @Override
    public boolean delete(Long id) {
        if(!tariffRepository.existsById(id)) {
            throw new EntityNotFoundException("Tariff not found with id " + id);
        }else {
            tariffRepository.deleteById(id);
            return true;
        }
    }
}
