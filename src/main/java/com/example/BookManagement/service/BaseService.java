package com.example.BookManagement.service;

import java.util.Optional;

public interface BaseService<T> {
    T save  (T t);
    Optional<T> findById(Long id);
    Iterable<T> findAll();
    void deleteById(Long id);
}
