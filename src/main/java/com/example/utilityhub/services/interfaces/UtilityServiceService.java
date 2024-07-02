package com.example.utilityhub.services.interfaces;

import com.example.utilityhub.dao.DAO;
import com.example.utilityhub.models.entities.UtilityService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UtilityServiceService extends DAO<UtilityService> {
    Page<UtilityService> getAllUtilityServicesPageable(Pageable pageable);
}
