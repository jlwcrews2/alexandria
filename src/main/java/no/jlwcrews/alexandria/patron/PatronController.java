package no.jlwcrews.alexandria.patron;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patron")
public class PatronController {

    private final PatronService patronService;

    @Autowired
    public PatronController(PatronService patronService) {
        this.patronService = patronService;
    }

    @GetMapping
    public ResponseEntity<List<Patron>> getPatrons() {
        return ResponseEntity.ok(patronService.getPatrons());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patron> getPatronById(@PathVariable long id) {
        return ResponseEntity.ok(patronService.getPatronById(id));
    }

    @PostMapping
    public ResponseEntity<Patron> savePatron(@RequestBody Patron patron) {
        return ResponseEntity.ok(patronService.savePatron(patron));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatronById(@PathVariable long id) {
        patronService.deletePatronById(id);
        return ResponseEntity.ok("Patron deleted");
    }
}
