package com.example.RentalService.external.client;


import com.example.RentalService.external.request.BorrowReturnBookRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "BOOK-SERVICE/book")
public interface BookService {

    @PutMapping("/borrow")
    ResponseEntity<Void> lendBook(
            @RequestBody BorrowReturnBookRequest request
    );
    @PutMapping("/return")
    ResponseEntity<Void> returnBook(
            @RequestBody BorrowReturnBookRequest request
    );
}
