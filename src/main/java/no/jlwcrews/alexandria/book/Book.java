package no.jlwcrews.alexandria.book;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import no.jlwcrews.alexandria.author.Author;
import no.jlwcrews.alexandria.bookevent.BookEvent;
import no.jlwcrews.alexandria.location.Location;
import no.jlwcrews.alexandria.models.Status;

import java.util.List;


@Entity
@Getter @Setter
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "book_gen")
    @SequenceGenerator(name = "book_gen", sequenceName = "book_seq", allocationSize = 1)
    private int bookId;
    private String title;
    private String publisher;
    private Status status;

    @ManyToOne
    @JoinColumn(name = "location_id")
    @JsonIgnoreProperties("books")
    private Location location;

    @ManyToMany
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    @JsonIgnoreProperties("books")
    private List<Author> authors;

    @OneToMany(mappedBy = "book")
    @JsonIgnoreProperties("book")
    private List<BookEvent> events;

    public Book(String title, String publisher, Status status, Location location, List<Author> authors) {
        this.title = title;
        this.publisher = publisher;
        this.status = status;
        this.location = location;
        this.authors = authors;
    }
}
