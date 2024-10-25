package no.jlwcrews.alexandria.location;

import jakarta.persistence.*;
import lombok.*;
import no.jlwcrews.alexandria.book.Book;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "location_gen")
    @SequenceGenerator(name = "location_gen", sequenceName = "location_seq", allocationSize = 1)
    @Column(name = "location_id", nullable = false)
    private Long location_id;
    private String shelf;

    @OneToMany(mappedBy = "location")
    private List<Book> books;

    public Location(String shelf) {
        this.shelf = shelf;
    }
}
