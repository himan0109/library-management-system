package com.bda.library.config;

import com.bda.library.entity.Author;
import com.bda.library.entity.Book;
import com.bda.library.repository.AuthorRepository;
import com.bda.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Override
    public void run(ApplicationArguments args) {
        if (authorRepository.count() > 0) return;

        // ── 10 Authors ──────────────────────────────────────────────────────────
        Author a1  = authorRepository.save(Author.builder().name("George Orwell").email("gorwell@mail.com").nationality("British").birthYear(1903).build());
        Author a2  = authorRepository.save(Author.builder().name("J.K. Rowling").email("jkrowling@mail.com").nationality("British").birthYear(1965).build());
        Author a3  = authorRepository.save(Author.builder().name("Fyodor Dostoevsky").email("dostoevsky@mail.com").nationality("Russian").birthYear(1821).build());
        Author a4  = authorRepository.save(Author.builder().name("Gabriel García Márquez").email("gmarquez@mail.com").nationality("Colombian").birthYear(1927).build());
        Author a5  = authorRepository.save(Author.builder().name("Toni Morrison").email("tmorrison@mail.com").nationality("American").birthYear(1931).build());
        Author a6  = authorRepository.save(Author.builder().name("Haruki Murakami").email("murakami@mail.com").nationality("Japanese").birthYear(1949).build());
        Author a7  = authorRepository.save(Author.builder().name("Leo Tolstoy").email("tolstoy@mail.com").nationality("Russian").birthYear(1828).build());
        Author a8  = authorRepository.save(Author.builder().name("Jane Austen").email("jausten@mail.com").nationality("British").birthYear(1775).build());
        Author a9  = authorRepository.save(Author.builder().name("Mark Twain").email("mtwain@mail.com").nationality("American").birthYear(1835).build());
        Author a10 = authorRepository.save(Author.builder().name("Franz Kafka").email("kafka@mail.com").nationality("Czech").birthYear(1883).build());

        // ── 10 Books ─────────────────────────────────────────────────────────────
        bookRepository.saveAll(List.of(
            Book.builder().title("1984").isbn("978-0451524935").publishedYear(1949).genre("Dystopian Fiction").author(a1).build(),
            Book.builder().title("Harry Potter and the Philosopher's Stone").isbn("978-0747532743").publishedYear(1997).genre("Fantasy").author(a2).build(),
            Book.builder().title("Crime and Punishment").isbn("978-0486454115").publishedYear(1866).genre("Psychological Fiction").author(a3).build(),
            Book.builder().title("One Hundred Years of Solitude").isbn("978-0060883287").publishedYear(1967).genre("Magical Realism").author(a4).build(),
            Book.builder().title("Beloved").isbn("978-1400033416").publishedYear(1987).genre("Historical Fiction").author(a5).build(),
            Book.builder().title("Norwegian Wood").isbn("978-0375704024").publishedYear(1987).genre("Literary Fiction").author(a6).build(),
            Book.builder().title("War and Peace").isbn("978-1400079988").publishedYear(1869).genre("Historical Fiction").author(a7).build(),
            Book.builder().title("Pride and Prejudice").isbn("978-0141439518").publishedYear(1813).genre("Romance").author(a8).build(),
            Book.builder().title("The Adventures of Tom Sawyer").isbn("978-0486400778").publishedYear(1876).genre("Adventure").author(a9).build(),
            Book.builder().title("The Metamorphosis").isbn("978-0486290300").publishedYear(1915).genre("Absurdist Fiction").author(a10).build()
        ));
    }
}
