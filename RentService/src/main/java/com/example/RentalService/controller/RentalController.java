package com.example.RentalService.controller;

import com.example.RentalService.business.RentalService;
import com.example.RentalService.domain.FinishedRentalsResponse;
import com.example.RentalService.domain.ReturnBookPaymentRequest;
import com.example.RentalService.external.response.RentalInfoResponse;
import com.example.RentalService.infrastructure.database.entity.RentalEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rental")
@AllArgsConstructor
public class RentalController {

    private final RentalService orderService;


    @GetMapping("/rentalInfo/{bookId}")
    public ResponseEntity<RentalInfoResponse> rentalInfo(
            @PathVariable("bookId") Integer bookId,
            @RequestBody String email
    ) {
        RentalInfoResponse response = orderService.rentalInfo(bookId, email);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/activeRentals/{pageNumber}/{pageSize}")
    public ResponseEntity<List<RentalEntity>> getActiveRental(
            @RequestBody String email,
            @PathVariable Integer pageNumber,
            @PathVariable Integer pageSize
    ) {
        return ResponseEntity.ok(orderService.getActiveRentals(email, pageNumber, pageSize));
    }

    @GetMapping("/finishedRentals/{pageNumber}/{pageSize}")
    public ResponseEntity<List<FinishedRentalsResponse>> getFinishedRental(
            @RequestBody String email,
            @PathVariable Integer pageNumber,
            @PathVariable Integer pageSize
    ) {
        return ResponseEntity.ok(orderService.getFinishedRentals(email, pageNumber, pageSize));
    }

    @PutMapping("/returnBook/{bookId}")
    public ResponseEntity<Void> returnBook(
            @PathVariable("bookId") Integer bookId,
            @RequestBody String email
    ) {
        orderService.returnBook(bookId, email);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("/returnBook/{bookId}")
    public ResponseEntity<Void> returnBookWithPayment(
            @PathVariable("bookId") Integer bookId,
            @RequestBody ReturnBookPaymentRequest request
    ) {
        orderService.returnBookWithPayment(bookId, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/lentBook/{bookId}")
    public ResponseEntity<Void> lentBook(
            @PathVariable("bookId") Integer bookId,
            @RequestBody String email
    ) {
        orderService.lentBook(bookId, email);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
