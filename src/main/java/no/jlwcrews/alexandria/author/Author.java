package no.jlwcrews.alexandria.author;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import no.jlwcrews.alexandria.book.Book;

import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_gen")
    @SequenceGenerator(name = "author_gen", sequenceName = "author_seq", allocationSize = 1)
    @Column(name = "author_id")
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    @ManyToMany(mappedBy = "authors")
    @JsonIgnoreProperties("authors")
    private List<Book> books;


    public Author(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }
}
