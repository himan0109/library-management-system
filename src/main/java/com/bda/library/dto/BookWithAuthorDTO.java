package com.bda.library.dto;

public class BookWithAuthorDTO {
    private Long bookId;
    private String title;
    private String isbn;
    private int publishedYear;
    private String genre;
    private Long authorId;
    private String authorName;
    private String authorNationality;

    public BookWithAuthorDTO() {}

    public BookWithAuthorDTO(Long bookId, String title, String isbn, int publishedYear,
                             String genre, Long authorId, String authorName, String authorNationality) {
        this.bookId = bookId;
        this.title = title;
        this.isbn = isbn;
        this.publishedYear = publishedYear;
        this.genre = genre;
        this.authorId = authorId;
        this.authorName = authorName;
        this.authorNationality = authorNationality;
    }

    public Long getBookId() { return bookId; }
    public void setBookId(Long bookId) { this.bookId = bookId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public int getPublishedYear() { return publishedYear; }
    public void setPublishedYear(int publishedYear) { this.publishedYear = publishedYear; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public Long getAuthorId() { return authorId; }
    public void setAuthorId(Long authorId) { this.authorId = authorId; }

    public String getAuthorName() { return authorName; }
    public void setAuthorName(String authorName) { this.authorName = authorName; }

    public String getAuthorNationality() { return authorNationality; }
    public void setAuthorNationality(String authorNationality) { this.authorNationality = authorNationality; }
}
