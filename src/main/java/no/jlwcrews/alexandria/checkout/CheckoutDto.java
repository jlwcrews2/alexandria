package no.jlwcrews.alexandria.checkout;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class CheckoutDto {
    private long bookId;
    private long patronId;

    public CheckoutDto(long bookId, long patronId) {
        this.bookId = bookId;
        this.patronId = patronId;
    }
}
