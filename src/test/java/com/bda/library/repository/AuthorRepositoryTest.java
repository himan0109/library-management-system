package com.bda.library.repository;

import com.bda.library.entity.Author;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    private Author author;

    @BeforeEach
    void setUp() {
        author = authorRepository.save(Author.builder()
                .name("George Orwell")
                .email("gorwell@test.com")
                .nationality("British")
                .birthYear(1903)
                .build());
    }

    @Test
    @DisplayName("save() persists an author and assigns an ID")
    void save_persistsAuthorWithId() {
        assertThat(author.getId()).isNotNull();
        assertThat(author.getName()).isEqualTo("George Orwell");
    }

    @Test
    @DisplayName("findByEmail() returns the matching author")
    void findByEmail_returnsAuthor() {
        Optional<Author> found = authorRepository.findByEmail("gorwell@test.com");
        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("George Orwell");
    }

    @Test
    @DisplayName("findByEmail() returns empty for unknown email")
    void findByEmail_returnsEmptyForUnknown() {
        Optional<Author> found = authorRepository.findByEmail("nobody@test.com");
        assertThat(found).isEmpty();
    }

    @Test
    @DisplayName("searchByName() returns author matching partial name (case-insensitive)")
    void searchByName_returnsPartialMatch() {
        authorRepository.save(Author.builder().name("J.K. Rowling").email("jkr@test.com").nationality("British").birthYear(1965).build());
        List<Author> results = authorRepository.searchByName("orwell");
        assertThat(results).hasSize(1);
        assertThat(results.get(0).getName()).isEqualTo("George Orwell");
    }

    @Test
    @DisplayName("findByNationality() returns authors for a given nationality")
    void findByNationality_returnsMatching() {
        authorRepository.save(Author.builder().name("J.K. Rowling").email("jkr@test.com").nationality("British").birthYear(1965).build());
        List<Author> british = authorRepository.findByNationality("British");
        assertThat(british).hasSizeGreaterThanOrEqualTo(2);
    }

    @Test
    @DisplayName("existsByEmail() returns true for existing email")
    void existsByEmail_trueForExisting() {
        assertThat(authorRepository.existsByEmail("gorwell@test.com")).isTrue();
    }

    @Test
    @DisplayName("existsByEmail() returns false for unknown email")
    void existsByEmail_falseForUnknown() {
        assertThat(authorRepository.existsByEmail("unknown@test.com")).isFalse();
    }

    @Test
    @DisplayName("findAll() returns all persisted authors")
    void findAll_returnsAllAuthors() {
        authorRepository.save(Author.builder().name("Toni Morrison").email("tm@test.com").nationality("American").birthYear(1931).build());
        assertThat(authorRepository.findAll()).hasSizeGreaterThanOrEqualTo(2);
    }

    @Test
    @DisplayName("deleteById() removes the author")
    void deleteById_removesAuthor() {
        authorRepository.deleteById(author.getId());
        assertThat(authorRepository.findById(author.getId())).isEmpty();
    }
}
