package no.jlwcrews.alexandria.patron;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Patron {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patron_gen")
    @SequenceGenerator(name = "patron_gen", sequenceName = "patron_seq", allocationSize = 1)
    @Column(name = "patron_id", nullable = false)
    private Long patronId;

    private String firstName;
    private String lastName;
    private LocalDate joinDate;

    public Patron(String firstName, String lastName, LocalDate joinDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.joinDate = joinDate;
    }
}
