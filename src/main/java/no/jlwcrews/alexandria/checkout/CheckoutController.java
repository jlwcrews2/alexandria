package no.jlwcrews.alexandria.checkout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CheckoutController {

    private final CheckoutService checkoutService;

    @Autowired
    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping("/checkout")
    public ResponseEntity<String> checkout(@RequestBody CheckoutDto checkoutDto) {
        checkoutService.checkout(checkoutDto);
        return ResponseEntity.ok("Book checkout out yo");
    }

    @PostMapping("/checkin")
    public ResponseEntity<String> checkin(@RequestBody CheckoutDto checkoutDto) {
        checkoutService.checkin(checkoutDto);
        return ResponseEntity.ok("Book checked in");
    }
}
