package com.example.RentalService.external.client;

import com.example.RentalService.domain.RentalServiceCustomException;
import com.example.RentalService.external.response.LibraryBookEntity;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "BOOK-SERVICE/library")
public interface BookService {

    @PutMapping("/rent/{id}")
    ResponseEntity<Void> lendBook(
            @PathVariable("id") Integer id
    );

    @PutMapping("/return/{id}")
    ResponseEntity<Void> returnBook(
            @RequestBody @PathVariable("id") Integer id
    );

    @GetMapping("/{id}")
    ResponseEntity<LibraryBookEntity> getBook(
            @PathVariable Integer id
    );
}
