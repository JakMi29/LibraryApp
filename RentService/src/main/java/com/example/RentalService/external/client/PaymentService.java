package com.example.RentalService.external.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@CircuitBreaker(name = "external", fallbackMethod = "fallback")
@FeignClient(name = "PAYMENT-SERVICE/payment")
public interface PaymentService {

    @PostMapping
    ResponseEntity<Void> lendBook(
            @PathVariable("id") Integer id
    );

    @GetMapping
    ResponseEntity<Void> returnBook(
            @RequestBody @PathVariable("id") Integer id
    );

  /*  default ResponseEntity<Void> fallback(Exception e) {
        throw new CustomException("Book Service is not available",
                "UNAVAILABLE",
                500);
    }*/

}
