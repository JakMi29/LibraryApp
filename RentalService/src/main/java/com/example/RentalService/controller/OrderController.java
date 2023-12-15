package com.example.RentalService.controller;

import com.example.RentalService.business.OrderService;
import com.example.RentalService.external.request.BorrowReturnBookRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/placeOrder/{bookId}")
    public ResponseEntity<Void> placeOrder(@PathVariable("bookId") Integer bookId) {
        orderService.placeOrder(bookId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/returnOrder/{bookId}")
    public ResponseEntity<Void> returnOrder(@PathVariable("bookId") Integer bookId) {
        orderService.returnOrder(bookId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
