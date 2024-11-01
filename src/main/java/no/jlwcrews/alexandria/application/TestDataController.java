package no.jlwcrews.alexandria.application;

import no.jlwcrews.alexandria.InitiData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testdata")
public class TestDataController {

    private final InitiData initiData;

    @Autowired
    public TestDataController(InitiData initiData) {
        this.initiData = initiData;
    }

    @GetMapping
    public ResponseEntity<String> createTestData(){
        initiData.createTestData();
        return ResponseEntity.ok("Test data created");
    }
}
