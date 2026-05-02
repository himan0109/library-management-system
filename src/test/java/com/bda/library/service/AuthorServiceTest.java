package com.bda.library.service;

import com.bda.library.entity.Author;
import com.bda.library.repository.AuthorRepository;
import com.bda.library.service.impl.AuthorServiceImpl;
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
class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorServiceImpl authorService;

    private Author author;

    @BeforeEach
    void setUp() {
        author = new Author();
        author.setId(1L); author.setName("George Orwell");
        author.setEmail("go@test.com"); author.setNationality("British");
        author.setBirthYear(1903);
    }

    @Test
    @DisplayName("findAll() delegates to repository and returns list")
    void findAll_returnsAllAuthors() {
        when(authorRepository.findAll()).thenReturn(List.of(author));
        assertThat(authorService.findAll()).hasSize(1).contains(author);
        verify(authorRepository).findAll();
    }

    @Test
    @DisplayName("findById() returns author when found")
    void findById_returnsAuthor() {
        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));
        assertThat(authorService.findById(1L)).isPresent().contains(author);
    }

    @Test
    @DisplayName("findById() returns empty when not found")
    void findById_returnsEmpty() {
        when(authorRepository.findById(99L)).thenReturn(Optional.empty());
        assertThat(authorService.findById(99L)).isEmpty();
    }

    @Test
    @DisplayName("save() persists and returns the author")
    void save_persistsAuthor() {
        when(authorRepository.save(author)).thenReturn(author);
        assertThat(authorService.save(author)).isEqualTo(author);
        verify(authorRepository).save(author);
    }

    @Test
    @DisplayName("update() patches fields of existing author")
    void update_patchesExistingAuthor() {
        Author updated = new Author();
        updated.setName("Orwell Updated"); updated.setEmail("new@test.com");
        updated.setNationality("American"); updated.setBirthYear(1910);

        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));
        when(authorRepository.save(any(Author.class))).thenAnswer(inv -> inv.getArgument(0));

        Author result = authorService.update(1L, updated);
        assertThat(result.getName()).isEqualTo("Orwell Updated");
        assertThat(result.getEmail()).isEqualTo("new@test.com");
    }

    @Test
    @DisplayName("update() throws EntityNotFoundException when author missing")
    void update_throwsWhenNotFound() {
        when(authorRepository.findById(99L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> authorService.update(99L, author))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("99");
    }

    @Test
    @DisplayName("deleteById() calls repository delete")
    void deleteById_callsRepository() {
        doNothing().when(authorRepository).deleteById(1L);
        authorService.deleteById(1L);
        verify(authorRepository).deleteById(1L);
    }

    @Test
    @DisplayName("existsByEmail() returns repository result")
    void existsByEmail_delegatesToRepository() {
        when(authorRepository.existsByEmail("go@test.com")).thenReturn(true);
        assertThat(authorService.existsByEmail("go@test.com")).isTrue();
    }

    @Test
    @DisplayName("searchByName() returns matching authors")
    void searchByName_returnsAuthors() {
        when(authorRepository.searchByName("orwell")).thenReturn(List.of(author));
        assertThat(authorService.searchByName("orwell")).hasSize(1);
    }
}
