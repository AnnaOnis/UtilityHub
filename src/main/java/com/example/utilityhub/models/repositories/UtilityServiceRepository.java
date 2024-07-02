package com.example.utilityhub.models.repositories;

import com.example.utilityhub.models.entities.UtilityService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilityServiceRepository  extends JpaRepository<UtilityService, Long> {
}
