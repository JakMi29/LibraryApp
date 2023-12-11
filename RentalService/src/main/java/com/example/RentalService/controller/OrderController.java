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

    @PostMapping("/placeOrder")
    public ResponseEntity<Void> placeOrder(@RequestBody BorrowReturnBookRequest Request) {
        orderService.placeOrder(Request);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/returnOrder")
    public ResponseEntity<Void> returnOrder(@RequestBody BorrowReturnBookRequest Request) {
        orderService.returnOrder(Request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
