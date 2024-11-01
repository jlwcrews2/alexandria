package no.jlwcrews.alexandria.book;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class BookTitleChangeDto {

    private String newTitle;
    private long bookId;
}
