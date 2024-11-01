package no.jlwcrews.alexandria.bookevent;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import no.jlwcrews.alexandria.book.Book;
import no.jlwcrews.alexandria.patron.Patron;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "book_event")
public class BookEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "book_event_gen")
    @SequenceGenerator(name = "book_event_gen", sequenceName = "book_event_seq", allocationSize = 1)
    private Long eventId;
    private LocalDateTime date;
    private BookEventType type;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "patron_id")
    private Patron patron;

    public BookEvent(LocalDateTime date, BookEventType type, Book book, Patron patron) {
        this.date = date;
        this.type = type;
        this.book = book;
        this.patron = patron;
    }
}
