package com.example.RentalService.external.client;


import com.example.RentalService.domain.CustomException;
import feign.FeignException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@CircuitBreaker(name = "external", fallbackMethod = "fallback")
@FeignClient(name = "BOOK-SERVICE/book")
public interface BookService {

    @PutMapping("/borrow/{id}")
    ResponseEntity<Void> lendBook(
            @PathVariable("id") Integer id
    );

    @PutMapping("/return/{id}")
    ResponseEntity<Void> returnBook(
            @RequestBody @PathVariable("id") Integer id
    );

  /*  default ResponseEntity<Void> fallback(Exception e) {
        throw new CustomException("Book Service is not available",
                "UNAVAILABLE",
                500);
    }*/

}
