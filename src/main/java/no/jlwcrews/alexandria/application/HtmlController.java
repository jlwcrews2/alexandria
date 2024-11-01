/*
package no.jlwcrews.alexandria.application;

import no.jlwcrews.alexandria.book.Book;
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

    private final LibraryService libraryService;

    @Autowired
    public HtmlController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping
    public String home(Model model) {
        model.addAttribute("books", libraryService.getBooks());
        return "index";
    }

    @GetMapping("/addNewBook")
    public String newBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "newBookForm";
    }

    @PostMapping("/addNewBook")
    public String addNewBook(@ModelAttribute Book book){
        libraryService.addNewBook(book);
        return "newBookResult";
    }
}
*/
