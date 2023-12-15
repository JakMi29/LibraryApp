package com.example.RentalService.external.client;


import com.example.RentalService.external.request.BorrowReturnBookRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "BOOK-SERVICE/book")
public interface BookService {

    @PutMapping("/borrow/{id}")
    ResponseEntity<Integer> lendBook(
            @PathVariable("id") Integer id
    );
    @PutMapping("/return/{id}")
    ResponseEntity<Integer> returnBook(
            @RequestBody @PathVariable("id") Integer id
    );
}
