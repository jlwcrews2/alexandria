package no.jlwcrews.alexandria.bookevent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookEventService {

    private final BookEventRepo bookeventRepo;

    @Autowired
    public BookEventService(BookEventRepo bookeventRepo) {
        this.bookeventRepo = bookeventRepo;
    }

    public List<BookEvent> getBookEvents() {
        return bookeventRepo.findAll();
    }

    public BookEvent getBookEventById(long id) {
        return bookeventRepo.findById(id).orElse(null);
    }

    public BookEvent saveBookEvent(BookEvent book) {
        return bookeventRepo.save(book);
    }

    public void deleteBookEventById(long id) {
        bookeventRepo.deleteById(id);
    }
}
