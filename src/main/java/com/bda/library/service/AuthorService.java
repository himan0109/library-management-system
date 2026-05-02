package com.bda.library.service;

import com.bda.library.entity.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> findAll();
    Optional<Author> findById(Long id);
    Author save(Author author);
    Author update(Long id, Author updated);
    void deleteById(Long id);
    boolean existsByEmail(String email);
    List<Author> searchByName(String name);
}
