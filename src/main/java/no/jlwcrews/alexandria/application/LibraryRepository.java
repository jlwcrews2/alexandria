package no.jlwcrews.alexandria.application;

import com.github.javafaker.Faker;
import no.jlwcrews.alexandria.models.Author;
import no.jlwcrews.alexandria.models.Book;
import no.jlwcrews.alexandria.models.Location;
import no.jlwcrews.alexandria.models.Status;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class LibraryRepository {

    private final List<Book> library = new ArrayList<>();

    public LibraryRepository() {
        for (int i = 0; i <50; i++) {
           library.add(new Book(
                   i,
                   Faker.instance().book().title(),
                   new Author(Faker.instance().name().firstName(), Faker.instance().name().lastName()),
                   Faker.instance().book().publisher(),
                   new Status(Faker.instance().bool().bool()),
                   new Location("Main building")
           ));
        }
    }

    public List<Book> getBooks() {
        return library;
    }

    public List<Book> getAvailableBooks() {
        return library
                .stream()
                .filter(book -> book.getStatus().isAvailable())
                .toList();
    }

    public Book getBookById(int id) {
        return library
                .stream()
                .filter(b -> b.getBookId() == id)
                .findFirst()
                .orElse(null);
    }

    public Book addBook(Book newBook) {
        library.add(newBook);
        return getBookById(newBook.getBookId());
    }
}
