package com.bda.library.service.impl;

import com.bda.library.dto.BookWithAuthorDTO;
import com.bda.library.entity.Author;
import com.bda.library.entity.Book;
import com.bda.library.repository.AuthorRepository;
import com.bda.library.repository.BookRepository;
import com.bda.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Book> findAll() {
        return bookRepository.findAllWithAuthor();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book save(Book book) {
        Author author = authorRepository.findById(book.getAuthor().getId())
                .orElseThrow(() -> new EntityNotFoundException("Author not found"));
        book.setAuthor(author);
        return bookRepository.save(book);
    }

    @Override
    public Book update(Long id, Book updated) {
        Book existing = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + id));
        Author author = authorRepository.findById(updated.getAuthor().getId())
                .orElseThrow(() -> new EntityNotFoundException("Author not found"));
        existing.setTitle(updated.getTitle());
        existing.setIsbn(updated.getIsbn());
        existing.setPublishedYear(updated.getPublishedYear());
        existing.setGenre(updated.getGenre());
        existing.setAuthor(author);
        return bookRepository.save(existing);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookWithAuthorDTO> findAllBooksWithAuthorDetails() {
        return bookRepository.findAllBooksWithAuthorDetails();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findByAuthorId(Long authorId) {
        return bookRepository.findByAuthorId(authorId);
    }
}
