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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class InitData implements CommandLineRunner {

    private final AuthorService authorService;
    private final BookService bookService;
    private final LocationService locationService;
    private final PatronService patronService;
    private final BookEventService bookEventService;

    Faker faker = Faker.instance();

    @Autowired
    public InitData(AuthorService authorService, BookService bookService, LocationService locationService, PatronService patronService, BookEventService bookEventService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.locationService = locationService;
        this.patronService = patronService;
        this.bookEventService = bookEventService;
    }

    @Override
    public void run(String... args) {

        // Create locations
        List<Location> locations = new ArrayList<>();
        for (long i = 0; i < 10; i++) {
            locations.add(locationService.save(
                    new Location(faker.numerify("Shelf ###"))
            ));
        }

        // Create authors
        List<Author> authors = new ArrayList<>();
        for (long i = 0; i < 10; i++) {
            authors.add(
                    authorService.addAuthor(
                            new Author(
                                    faker.name().firstName(),
                                    faker.name().lastName()
                            )
                    )
            );
        }

        // Create books
        List<Book> books = new ArrayList<>();
        for (long i = 0; i < 20; i++) {
            books.add(bookService.save(
                            new Book(
                                    faker.book().title(),
                                    faker.book().publisher(),
                                    Status.AVAILABLE,
                                    getRandomAuthors(authors),
                                    getRandomLocation(locations)
                            )
                    )
            );
        }

        //create patrons
        for (int i = 0; i < 25; i++) {
            patronService.save(
                    new Patron(
                            faker.name().firstName(),
                            faker.name().lastName(),
                            LocalDate.ofInstant(faker.date().birthday().toInstant(), ZoneId.systemDefault())
                    )
            );

        }

        //create events
        for (Book book : books) {
            bookEventService.save(
                    new BookEvent(
                            BookEventType.ACQUIRE,
                            LocalDateTime.now(),
                            book,
                            null
                    )
            );
        }
    }

    private Status getRandomStatus() {
        return Status.values()[new Random().nextInt(Status.values().length)];
    }

    private List<Author> getRandomAuthors(List<Author> authors) {
        List<Author> randomAuthors = new ArrayList<>();
        for (int i = 0; i < new Random().nextInt(1, 3); i++) {
            randomAuthors.add(authors.get(new Random().nextInt(authors.size())));
        }
        return randomAuthors;
    }

    private Location getRandomLocation(List<Location> locations) {
        return locations.get(new Random().nextInt(locations.size()));
    }
}
