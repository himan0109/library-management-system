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

    private Author makeAuthor(String name, String email, String nationality, int birthYear) {
        Author a = new Author();
        a.setName(name); a.setEmail(email);
        a.setNationality(nationality); a.setBirthYear(birthYear);
        return a;
    }

    @BeforeEach
    void setUp() {
        author = authorRepository.save(makeAuthor("George Orwell", "gorwell@test.com", "British", 1903));
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
        assertThat(authorRepository.findByEmail("nobody@test.com")).isEmpty();
    }

    @Test
    @DisplayName("searchByName() returns author matching partial name (case-insensitive)")
    void searchByName_returnsPartialMatch() {
        authorRepository.save(makeAuthor("J.K. Rowling", "jkr@test.com", "British", 1965));
        List<Author> results = authorRepository.searchByName("orwell");
        assertThat(results).hasSize(1);
        assertThat(results.get(0).getName()).isEqualTo("George Orwell");
    }

    @Test
    @DisplayName("findByNationality() returns authors for a given nationality")
    void findByNationality_returnsMatching() {
        authorRepository.save(makeAuthor("J.K. Rowling", "jkr@test.com", "British", 1965));
        assertThat(authorRepository.findByNationality("British")).hasSizeGreaterThanOrEqualTo(2);
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
    @DisplayName("deleteById() removes the author")
    void deleteById_removesAuthor() {
        authorRepository.deleteById(author.getId());
        assertThat(authorRepository.findById(author.getId())).isEmpty();
    }
}
