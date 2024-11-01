package no.jlwcrews.alexandria.book;

import no.jlwcrews.alexandria.author.Author;
import no.jlwcrews.alexandria.author.AuthorService;
import no.jlwcrews.alexandria.bookevent.BookEvent;
import no.jlwcrews.alexandria.bookevent.BookEventService;
import no.jlwcrews.alexandria.bookevent.BookEventType;
import no.jlwcrews.alexandria.location.Location;
import no.jlwcrews.alexandria.location.LocationService;
import no.jlwcrews.alexandria.models.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private final BookRepo bookRepo;
    private final LocationService locationService;
    private final AuthorService authorService;
    private final BookEventService bookEventService;

    @Autowired
    public BookService(BookRepo bookRepo, LocationService locationService, AuthorService authorService, BookEventService bookEventService) {
        this.bookRepo = bookRepo;
        this.locationService = locationService;
        this.authorService = authorService;
        this.bookEventService = bookEventService;
    }

    public List<Book> getBooks() {
        return bookRepo.findAll();
    }

    public Book getBookById(long id) {
        return bookRepo.findById(id).orElse(null);
    }

    public Book saveBook(Book book) {
        return bookRepo.save(book);
    }

    public Book saveBook(BookDto bookDto) {
        Location location = locationService.getLocationById(bookDto.getLocationId());
        List<Author> authors = new ArrayList<>();
        for (Long authorId: bookDto.getAuthorIds()) {
            authors.add(authorService.getAuthorById(authorId));
        }

        Book book = bookRepo.save(new Book(
                bookDto.getTitle(),
                bookDto.getPublisher(),
                Status.AVAILABLE,
                location,
                authors
        ));

        bookEventService.saveBookEvent(
                new BookEvent(
                        LocalDateTime.now(),
                        BookEventType.ACQUIRED,
                        book,
                        null
                )
        );

        return bookRepo.findById(book.getBookId()).orElse(null);
    }

    public void deleteBookById(long id) {
        bookRepo.deleteById(id);
    }

    public Book changeBookTitle(BookTitleChangeDto dto) {
        Book book = bookRepo.findById(dto.getBookId()).orElse(null);
        book.setTitle(dto.getNewTitle());
        return bookRepo.save(book);
    }
}
