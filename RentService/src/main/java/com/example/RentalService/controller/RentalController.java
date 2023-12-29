package com.example.RentalService.controller;

import com.example.RentalService.business.RentalService;
import com.example.RentalService.external.response.RentalInfoResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rental")
@AllArgsConstructor
public class RentalController {

    private final RentalService orderService;

    @GetMapping
    public ResponseEntity<String> getMethod() {
        return ResponseEntity.ok("hello");
    }

    @PostMapping("/lentBook/{bookId}")
    public ResponseEntity<Void> placeOrder(
            @PathVariable("bookId") Integer bookId,
            @RequestBody String email
    ) {
        orderService.placeOrder(bookId, email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/rentalInfo/{bookId}")
    public ResponseEntity<RentalInfoResponse> rentalInfo(
            @PathVariable("bookId") Integer bookId,
            @RequestBody String email
    ) {
        RentalInfoResponse response = orderService.rentalInfo(bookId, email);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/returnBook/{bookId}")
    public ResponseEntity<Void> returnBook(
            @PathVariable("bookId") Integer bookId,
            @RequestBody String email
    ) {
        orderService.returnOrder(bookId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/returnBook/{bookId}")
    public ResponseEntity<Void> returnBookWithPayment(
            @PathVariable("bookId") Integer bookId,
            @RequestBody String email
    ) {
        orderService.returnOrder(bookId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
