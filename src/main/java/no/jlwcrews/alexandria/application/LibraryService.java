package no.jlwcrews.alexandria.application;

import no.jlwcrews.alexandria.application.error.BookNotFoundException;
import no.jlwcrews.alexandria.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryService {

    private final LibraryRepository repo;

    @Autowired
    public LibraryService(LibraryRepository repo){
        this.repo = repo;
    }

    public List<Book> getBooks(){
        return repo.getBooks();
    }

    public List<Book> getAvailableBooks(){
        return repo.getAvailableBooks();
    }

    public Book getBookById(int id) throws BookNotFoundException {
        return repo.getBookById(id);
    }

    public Book addNewBook(Book newBook) {
        return repo.addBook(newBook);
    }
}
