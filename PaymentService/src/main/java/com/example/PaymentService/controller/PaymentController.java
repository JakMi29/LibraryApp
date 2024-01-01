package com.example.PaymentService.controller;

import com.example.PaymentService.business.PaymentService;
import com.example.PaymentService.domain.PaymentInfoRequest;
import com.example.PaymentService.domain.PaymentInfoResponse;
import com.example.PaymentService.domain.PaymentRequest;
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

    @GetMapping("/info")
    public ResponseEntity<PaymentInfoResponse> getPaymentInfo(
            @RequestParam Integer referenceId,
            @RequestParam String transactionType) {
        PaymentInfoRequest paymentInfoRequest=PaymentInfoRequest.builder()
                .referenceId(referenceId)
                .transactionType(transactionType)
                .build();
        return ResponseEntity.ok(paymentService.getPaymentInfo(paymentInfoRequest));
    }
}