package com.example.PaymentService.controller;

import com.example.PaymentService.domain.PaymentRequest;
import com.example.PaymentService.business.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<Void> doPayment(@RequestBody PaymentRequest paymentRequest) {
        paymentService.doPayment(paymentRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<Void> getPaymentInfo(@RequestBody PaymentRequest paymentRequest) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}