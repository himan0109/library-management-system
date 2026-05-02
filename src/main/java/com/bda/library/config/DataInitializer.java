package com.bda.library.config;

import com.bda.library.entity.Author;
import com.bda.library.entity.Book;
import com.bda.library.repository.AuthorRepository;
import com.bda.library.repository.BookRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataInitializer implements ApplicationRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public DataInitializer(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    private Author makeAuthor(String name, String email, String nationality, int birthYear) {
        Author a = new Author();
        a.setName(name); a.setEmail(email);
        a.setNationality(nationality); a.setBirthYear(birthYear);
        return authorRepository.save(a);
    }

    private Book makeBook(String title, String isbn, int year, String genre, Author author) {
        Book b = new Book();
        b.setTitle(title); b.setIsbn(isbn);
        b.setPublishedYear(year); b.setGenre(genre); b.setAuthor(author);
        return b;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (authorRepository.count() > 0) return;

        Author a1  = makeAuthor("George Orwell",           "gorwell@mail.com",    "British",    1903);
        Author a2  = makeAuthor("J.K. Rowling",             "jkrowling@mail.com",  "British",    1965);
        Author a3  = makeAuthor("Fyodor Dostoevsky",        "dostoevsky@mail.com", "Russian",    1821);
        Author a4  = makeAuthor("Gabriel Garcia Marquez",   "gmarquez@mail.com",   "Colombian",  1927);
        Author a5  = makeAuthor("Toni Morrison",            "tmorrison@mail.com",  "American",   1931);
        Author a6  = makeAuthor("Haruki Murakami",          "murakami@mail.com",   "Japanese",   1949);
        Author a7  = makeAuthor("Leo Tolstoy",              "tolstoy@mail.com",    "Russian",    1828);
        Author a8  = makeAuthor("Jane Austen",              "jausten@mail.com",    "British",    1775);
        Author a9  = makeAuthor("Mark Twain",               "mtwain@mail.com",     "American",   1835);
        Author a10 = makeAuthor("Franz Kafka",              "kafka@mail.com",      "Czech",      1883);

        bookRepository.saveAll(Arrays.asList(
            makeBook("1984",                                   "978-0451524935", 1949, "Dystopian Fiction",     a1),
            makeBook("Harry Potter and the Philosopher Stone", "978-0747532743", 1997, "Fantasy",               a2),
            makeBook("Crime and Punishment",                   "978-0486454115", 1866, "Psychological Fiction", a3),
            makeBook("One Hundred Years of Solitude",          "978-0060883287", 1967, "Magical Realism",       a4),
            makeBook("Beloved",                                "978-1400033416", 1987, "Historical Fiction",    a5),
            makeBook("Norwegian Wood",                         "978-0375704024", 1987, "Literary Fiction",      a6),
            makeBook("War and Peace",                          "978-1400079988", 1869, "Historical Fiction",    a7),
            makeBook("Pride and Prejudice",                    "978-0141439518", 1813, "Romance",               a8),
            makeBook("The Adventures of Tom Sawyer",           "978-0486400778", 1876, "Adventure",             a9),
            makeBook("The Metamorphosis",                      "978-0486290300", 1915, "Absurdist Fiction",     a10)
        ));
    }
}
