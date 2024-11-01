package no.jlwcrews.alexandria.location;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import no.jlwcrews.alexandria.book.Book;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter @Setter
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "location_gen")
    @SequenceGenerator(name = "location_gen", sequenceName = "location_seq", allocationSize = 1)
    private long location_id;
    private String location;

    @OneToMany(mappedBy = "location")
    @JsonIgnoreProperties("location")
    private List<Book> books;

    public Location(String location) {
        this.location = location;
    }
}
