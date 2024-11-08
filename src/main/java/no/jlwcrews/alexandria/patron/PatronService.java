package no.jlwcrews.alexandria.patron;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatronService {

    private final PatronRepo patronRepo;

    @Autowired
    public PatronService(PatronRepo patronRepo) {
        this.patronRepo = patronRepo;
    }

    public List<Patron> getPatrons() {
        return patronRepo.findAll();
    }

    public Patron getPatronById(long id) {
        return patronRepo.findById(id).orElse(null);
    }

    public Patron savePatron(Patron author) {
        return patronRepo.save(author);
    }

    public void deletePatronById(long id) {
        patronRepo.deleteById(id);
    }

    public void deleteAllPatrons() {
        patronRepo.deleteAll();
    }
}
