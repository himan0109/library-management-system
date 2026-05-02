package com.bda.library.service.impl;

import com.bda.library.entity.Author;
import com.bda.library.repository.AuthorRepository;
import com.bda.library.service.AuthorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public Author save(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author update(Long id, Author updated) {
        Author existing = authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Author not found with id: " + id));
        existing.setName(updated.getName());
        existing.setEmail(updated.getEmail());
        existing.setNationality(updated.getNationality());
        existing.setBirthYear(updated.getBirthYear());
        return authorRepository.save(existing);
    }

    @Override
    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) {
        return authorRepository.existsByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> searchByName(String name) {
        return authorRepository.searchByName(name);
    }
}
