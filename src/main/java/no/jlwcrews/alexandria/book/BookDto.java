package no.jlwcrews.alexandria.book;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class BookDto {
        private String title;
        private String publisher;
        private long locationId;
        private List<Long> authorIds;
}
