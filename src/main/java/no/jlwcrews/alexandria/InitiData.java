package no.jlwcrews.alexandria;

import com.github.javafaker.Faker;
import no.jlwcrews.alexandria.author.Author;
import no.jlwcrews.alexandria.author.AuthorService;
import no.jlwcrews.alexandria.book.Book;
import no.jlwcrews.alexandria.book.BookService;
import no.jlwcrews.alexandria.bookevent.BookEvent;
import no.jlwcrews.alexandria.bookevent.BookEventService;
import no.jlwcrews.alexandria.bookevent.BookEventType;
import no.jlwcrews.alexandria.location.Location;
import no.jlwcrews.alexandria.location.LocationService;
import no.jlwcrews.alexandria.models.Status;
import no.jlwcrews.alexandria.patron.Patron;
import no.jlwcrews.alexandria.patron.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class InitiData implements CommandLineRunner {

    private final LocationService locationService;
    private final AuthorService authorService;
    private final BookService bookService;
    private final PatronService patronService;
    private final BookEventService bookEventService;
    private final Faker faker = Faker.instance();
    private final Random rng = new Random();

    @Autowired
    public InitiData(LocationService locationService, AuthorService authorService, BookService bookService, PatronService patronService, BookEventService bookEventService) {
        this.locationService = locationService;
        this.authorService = authorService;
        this.bookService = bookService;
        this.patronService = patronService;
        this.bookEventService = bookEventService;
    }

    @Override
    public void run(String... args) throws Exception {

        //create locations
        List<Location> locations = new ArrayList<>();
        for (long i = 0; i < 10; i++) {
            locations.add(
                    locationService.saveLocation(
                            new Location(
                                    faker.numerify("Shelf ###")
                            )
                    )
            );
        }

        //create authors
        List<Author> authors = new ArrayList<>();
        for (long i = 0; i < 20; i++) {
            authors.add(
                    authorService.saveAuthor(
                            new Author(
                                    faker.name().firstName(),
                                    faker.name().lastName()
                            )
                    )

            );
        }

        //create books
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            Book book = bookService.saveBook(
                    new Book(
                            faker.book().title(),
                            faker.book().publisher(),
                            Status.AVAILABLE,
                            getRandomLocation(locations),
                            getRandomAuthors(authors)
                    )
            );
            books.add(book);
        }

        //create patrons
        for (int i = 0; i < 20; i++) {
            patronService.savePatron(
                    new Patron(
                            faker.name().firstName(),
                            faker.name().lastName()
                    )
            );
        }

        //create events
        for (Book book : books) {
            bookEventService.saveBookEvent(new BookEvent(
                    LocalDateTime.now(),
                    BookEventType.ACQUIRED,
                    book,
                    null
            ));
        }
    }

    private List<Author> getRandomAuthors(List<Author> authors) {
        List<Author> randomAuthors = new ArrayList<>();
        for (int i = 0; i < rng.nextInt(1, 3); i++) {
            randomAuthors.add(
                    authors.get(rng.nextInt(authors.size()))
            );
        }
        return randomAuthors;
    }

    private Location getRandomLocation(List<Location> locations) {
        return locations.get(rng.nextInt(locations.size()));
    }
}
