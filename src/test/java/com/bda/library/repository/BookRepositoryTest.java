package com.bda.library.repository;

import com.bda.library.dto.BookWithAuthorDTO;
import com.bda.library.entity.Author;
import com.bda.library.entity.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BookRepositoryTest {

    @Autowired private BookRepository  bookRepository;
    @Autowired private AuthorRepository authorRepository;

    private Author author;
    private Book   book;

    @BeforeEach
    void setUp() {
        author = authorRepository.save(Author.builder()
                .name("George Orwell").email("go@test.com")
                .nationality("British").birthYear(1903).build());
        book = bookRepository.save(Book.builder()
                .title("1984").isbn("978-0451524935")
                .publishedYear(1949).genre("Dystopian Fiction").author(author).build());
    }

    @Test
    @DisplayName("save() persists a book with author reference")
    void save_persistsBookWithAuthor() {
        assertThat(book.getId()).isNotNull();
        assertThat(book.getAuthor().getName()).isEqualTo("George Orwell");
    }

    @Test
    @DisplayName("findByAuthorId() returns books for a given author")
    void findByAuthorId_returnsBooks() {
        List<Book> books = bookRepository.findByAuthorId(author.getId());
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getTitle()).isEqualTo("1984");
    }

    @Test
    @DisplayName("existsByIsbn() returns true for existing ISBN")
    void existsByIsbn_trueForExisting() {
        assertThat(bookRepository.existsByIsbn("978-0451524935")).isTrue();
    }

    @Test
    @DisplayName("existsByIsbn() returns false for unknown ISBN")
    void existsByIsbn_falseForUnknown() {
        assertThat(bookRepository.existsByIsbn("000-0000000000")).isFalse();
    }

    @Test
    @DisplayName("findAllBooksWithAuthorDetails() inner join returns DTO rows")
    void findAllBooksWithAuthorDetails_returnsJoinResult() {
        List<BookWithAuthorDTO> dtos = bookRepository.findAllBooksWithAuthorDetails();
        assertThat(dtos).isNotEmpty();
        BookWithAuthorDTO dto = dtos.get(0);
        assertThat(dto.getTitle()).isEqualTo("1984");
        assertThat(dto.getAuthorName()).isEqualTo("George Orwell");
        assertThat(dto.getAuthorNationality()).isEqualTo("British");
    }

    @Test
    @DisplayName("findAllWithAuthor() fetch join loads author eagerly")
    void findAllWithAuthor_loadsAuthorEagerly() {
        List<Book> books = bookRepository.findAllWithAuthor();
        assertThat(books).isNotEmpty();
        assertThat(books.get(0).getAuthor()).isNotNull();
    }

    @Test
    @DisplayName("findByGenre() returns books matching genre")
    void findByGenre_returnsMatchingBooks() {
        List<Book> books = bookRepository.findByGenre("Dystopian Fiction");
        assertThat(books).hasSize(1);
    }
}
