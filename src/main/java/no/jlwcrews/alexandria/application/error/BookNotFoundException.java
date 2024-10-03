package no.jlwcrews.alexandria.application.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No book found")
public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(int message) {
        super(String.valueOf(message));
    }
}
