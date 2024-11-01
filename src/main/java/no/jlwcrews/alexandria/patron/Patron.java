package no.jlwcrews.alexandria.patron;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import no.jlwcrews.alexandria.bookevent.BookEvent;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@Entity
public class Patron {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "patron_gen")
    @SequenceGenerator(name = "patron_gen", sequenceName = "patron_seq", allocationSize = 1)
    private Long patronId;
    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "patron")
    private List<BookEvent> events;

    public Patron(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
