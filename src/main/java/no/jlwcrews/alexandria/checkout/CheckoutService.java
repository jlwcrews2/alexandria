package no.jlwcrews.alexandria.checkout;

import no.jlwcrews.alexandria.book.Book;
import no.jlwcrews.alexandria.book.BookService;
import no.jlwcrews.alexandria.bookevent.BookEvent;
import no.jlwcrews.alexandria.bookevent.BookEventService;
import no.jlwcrews.alexandria.bookevent.BookEventType;
import no.jlwcrews.alexandria.models.Status;
import no.jlwcrews.alexandria.patron.Patron;
import no.jlwcrews.alexandria.patron.PatronService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CheckoutService {

    private final BookService bookService;
    private final BookEventService bookEventService;
    private final PatronService patronService;


    public CheckoutService(BookService bookService, BookEventService bookEventService, PatronService patronService) {
        this.bookService = bookService;
        this.bookEventService = bookEventService;
        this.patronService = patronService;
    }

    public void checkout(CheckoutDto checkoutDto){
        Patron patron = patronService.getPatronById(checkoutDto.getPatronId());
        Book book = bookService.getBookById(checkoutDto.getBookId());
        bookEventService.saveBookEvent(new BookEvent(
                LocalDateTime.now(),
                BookEventType.CHECKED_OUT,
                book,
                patron
        ));
        book.setStatus(Status.CHECKED_OUT);
        bookService.saveBook(book);
    }

    public void checkin(CheckoutDto checkoutDto){
        Patron patron = patronService.getPatronById(checkoutDto.getPatronId());
        Book book = bookService.getBookById(checkoutDto.getBookId());
        bookEventService.saveBookEvent(new BookEvent(
                LocalDateTime.now(),
                BookEventType.CHECKED_IN,
                book,
                patron
        ));
        book.setStatus(Status.AVAILABLE);
        bookService.saveBook(book);
    }
}
