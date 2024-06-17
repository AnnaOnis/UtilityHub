package com.example.utilityhub.repositories;

import com.example.utilityhub.models.Tariff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TariffRepository  extends JpaRepository<Tariff, Long> {
}
