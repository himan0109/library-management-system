package com.bda.library.entity;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    @Size(max = 200, message = "Title must not exceed 200 characters")
    @Column(nullable = false, length = 200)
    private String title;

    @NotBlank(message = "ISBN is required")
    @Pattern(regexp = "^[0-9\\-]{10,17}$", message = "ISBN must be 10-17 digits/hyphens")
    @Column(nullable = false, unique = true, length = 20)
    private String isbn;

    @Min(value = 1000, message = "Published year must be a valid year")
    @Max(value = 2025, message = "Published year cannot be in the future")
    @Column(name = "published_year", nullable = false)
    private int publishedYear;

    @NotBlank(message = "Genre is required")
    @Size(max = 80, message = "Genre must not exceed 80 characters")
    @Column(nullable = false, length = 80)
    private String genre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    @NotNull(message = "Author is required")
    private Author author;

    public Book() {}

    public Book(Long id, String title, String isbn, int publishedYear, String genre, Author author) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.publishedYear = publishedYear;
        this.genre = genre;
        this.author = author;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public int getPublishedYear() { return publishedYear; }
    public void setPublishedYear(int publishedYear) { this.publishedYear = publishedYear; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public Author getAuthor() { return author; }
    public void setAuthor(Author author) { this.author = author; }
}
