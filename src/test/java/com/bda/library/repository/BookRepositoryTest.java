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

    @Autowired private BookRepository   bookRepository;
    @Autowired private AuthorRepository authorRepository;

    private Author author;
    private Book   book;

    @BeforeEach
    void setUp() {
        Author a = new Author();
        a.setName("George Orwell"); a.setEmail("go@test.com");
        a.setNationality("British"); a.setBirthYear(1903);
        author = authorRepository.save(a);

        Book b = new Book();
        b.setTitle("1984"); b.setIsbn("978-0451524935");
        b.setPublishedYear(1949); b.setGenre("Dystopian Fiction"); b.setAuthor(author);
        book = bookRepository.save(b);
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
        assertThat(dtos.get(0).getTitle()).isEqualTo("1984");
        assertThat(dtos.get(0).getAuthorName()).isEqualTo("George Orwell");
        assertThat(dtos.get(0).getAuthorNationality()).isEqualTo("British");
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
        assertThat(bookRepository.findByGenre("Dystopian Fiction")).hasSize(1);
    }
}
