package no.jlwcrews.alexandria.application;

import no.jlwcrews.alexandria.InitData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testdata")
public class TestDataController {

    private final InitData initData;

    @Autowired
    public TestDataController(InitData initData) {
        this.initData = initData;
    }

    @GetMapping
    public ResponseEntity<String> createTestData(){
        initData.createTestData();
        return ResponseEntity.ok("Test data created");
    }
}
