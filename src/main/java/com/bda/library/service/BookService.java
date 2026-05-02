package com.bda.library.service;

import com.bda.library.dto.BookWithAuthorDTO;
import com.bda.library.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();
    Optional<Book> findById(Long id);
    Book save(Book book);
    Book update(Long id, Book updated);
    void deleteById(Long id);
    List<BookWithAuthorDTO> findAllBooksWithAuthorDetails();
    List<Book> findByAuthorId(Long authorId);
}
