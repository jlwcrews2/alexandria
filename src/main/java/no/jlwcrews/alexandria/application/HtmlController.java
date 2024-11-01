
package no.jlwcrews.alexandria.application;

import no.jlwcrews.alexandria.book.Book;
import no.jlwcrews.alexandria.book.BookDto;
import no.jlwcrews.alexandria.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/html")
public class HtmlController {

    private final BookService bookService;

    @Autowired
    public HtmlController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String home(Model model) {
        model.addAttribute("books", bookService.getBooks());
        return "index";
    }

    @GetMapping("/addNewBook")
    public String newBookForm(Model model) {
        model.addAttribute("book", new BookDto());
        return "newBookForm";
    }

    @PostMapping("/addNewBook")
    public String addNewBook(@ModelAttribute Book book){
        bookService.saveBook(book);
        return "newBookResult";
    }
}

