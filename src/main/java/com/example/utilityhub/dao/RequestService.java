package com.example.utilityhub.dao;

import com.example.utilityhub.models.Request;

import java.util.List;

public interface RequestService extends DAO<Request>{
    List<Request> getRequestsByUserId(Long id);
}
