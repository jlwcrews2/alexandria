package no.jlwcrews.alexandria.book;

import no.jlwcrews.alexandria.book.Book;
import no.jlwcrews.alexandria.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getBooks(){
        return ResponseEntity.ok(bookService.getBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable long id){
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @PostMapping
    public ResponseEntity<Book> saveBook(@RequestBody BookDto book) {
        return ResponseEntity.ok(bookService.saveBook(book));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable long id) {
        bookService.deleteBookById(id);
        return ResponseEntity.ok("Book deleted");
    }

    @PutMapping("/change/title")
    public ResponseEntity<Book> changeBookTitle(BookTitleChangeDto dto) {
        return ResponseEntity.ok(bookService.changeBookTitle(dto));
    }
}
