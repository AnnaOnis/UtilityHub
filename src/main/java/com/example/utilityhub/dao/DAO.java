package com.example.utilityhub.dao;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    T save(T t);
    List<T> getAll();
    Optional<T> findById(Long id);
    T update(T t);
    boolean delete(Long id);
}