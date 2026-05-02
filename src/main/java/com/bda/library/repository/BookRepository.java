package com.bda.library.repository;

import com.bda.library.dto.BookWithAuthorDTO;
import com.bda.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByAuthorId(Long authorId);

    List<Book> findByGenre(String genre);

    boolean existsByIsbn(String isbn);

    /**
     * INNER JOIN between books and authors, returning a flat DTO
     * (satisfies the custom join-query requirement).
     */
    @Query("""
           SELECT new com.bda.library.dto.BookWithAuthorDTO(
               b.id, b.title, b.isbn, b.publishedYear, b.genre,
               a.id, a.name, a.nationality)
           FROM Book b INNER JOIN b.author a
           ORDER BY a.name, b.title
           """)
    List<BookWithAuthorDTO> findAllBooksWithAuthorDetails();

    @Query("SELECT b FROM Book b INNER JOIN FETCH b.author a ORDER BY b.title")
    List<Book> findAllWithAuthor();
}
