package com.bda.library.service;

import com.bda.library.dto.BookWithAuthorDTO;
import com.bda.library.entity.Author;
import com.bda.library.entity.Book;
import com.bda.library.repository.AuthorRepository;
import com.bda.library.repository.BookRepository;
import com.bda.library.service.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock private BookRepository   bookRepository;
    @Mock private AuthorRepository authorRepository;

    @InjectMocks private BookServiceImpl bookService;

    private Author author;
    private Book   book;

    @BeforeEach
    void setUp() {
        author = Author.builder().id(1L).name("George Orwell").email("go@test.com")
                .nationality("British").birthYear(1903).build();
        book = Book.builder().id(1L).title("1984").isbn("978-0451524935")
                .publishedYear(1949).genre("Dystopian Fiction").author(author).build();
    }

    @Test
    @DisplayName("findAll() uses fetch-join query")
    void findAll_usesJoinFetch() {
        when(bookRepository.findAllWithAuthor()).thenReturn(List.of(book));
        List<Book> result = bookService.findAll();
        assertThat(result).hasSize(1).contains(book);
        verify(bookRepository).findAllWithAuthor();
    }

    @Test
    @DisplayName("findById() returns book when found")
    void findById_returnsBook() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        assertThat(bookService.findById(1L)).isPresent().contains(book);
    }

    @Test
    @DisplayName("save() resolves author by ID before persisting")
    void save_resolvesAuthorAndSaves() {
        Book input = Book.builder().title("1984").isbn("978-0451524935")
                .publishedYear(1949).genre("Dystopian Fiction")
                .author(Author.builder().id(1L).build()).build();
        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        Book saved = bookService.save(input);
        assertThat(saved.getAuthor().getName()).isEqualTo("George Orwell");
    }

    @Test
    @DisplayName("save() throws EntityNotFoundException when author is missing")
    void save_throwsWhenAuthorMissing() {
        Book input = Book.builder().title("X").isbn("000").publishedYear(2000)
                .genre("G").author(Author.builder().id(99L).build()).build();
        when(authorRepository.findById(99L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> bookService.save(input))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    @DisplayName("update() patches all fields of existing book")
    void update_patchesExistingBook() {
        Book updated = Book.builder().title("Animal Farm").isbn("978-0451526342")
                .publishedYear(1945).genre("Political Satire")
                .author(Author.builder().id(1L).build()).build();

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));
        when(bookRepository.save(any(Book.class))).thenAnswer(inv -> inv.getArgument(0));

        Book result = bookService.update(1L, updated);
        assertThat(result.getTitle()).isEqualTo("Animal Farm");
        assertThat(result.getIsbn()).isEqualTo("978-0451526342");
        assertThat(result.getGenre()).isEqualTo("Political Satire");
    }

    @Test
    @DisplayName("update() throws EntityNotFoundException when book not found")
    void update_throwsWhenBookNotFound() {
        when(bookRepository.findById(99L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> bookService.update(99L, book))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("99");
    }

    @Test
    @DisplayName("findAllBooksWithAuthorDetails() returns DTO list from repository")
    void findAllBooksWithAuthorDetails_returnsDTOs() {
        BookWithAuthorDTO dto = new BookWithAuthorDTO(1L, "1984", "978-0451524935",
                1949, "Dystopian Fiction", 1L, "George Orwell", "British");
        when(bookRepository.findAllBooksWithAuthorDetails()).thenReturn(List.of(dto));

        List<BookWithAuthorDTO> result = bookService.findAllBooksWithAuthorDetails();
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getAuthorName()).isEqualTo("George Orwell");
    }

    @Test
    @DisplayName("deleteById() calls repository delete")
    void deleteById_callsRepository() {
        doNothing().when(bookRepository).deleteById(1L);
        bookService.deleteById(1L);
        verify(bookRepository).deleteById(1L);
    }

    @Test
    @DisplayName("findByAuthorId() delegates to repository")
    void findByAuthorId_returnsBooks() {
        when(bookRepository.findByAuthorId(1L)).thenReturn(List.of(book));
        assertThat(bookService.findByAuthorId(1L)).hasSize(1);
    }
}
