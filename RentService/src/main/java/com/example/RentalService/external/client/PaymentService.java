package com.example.RentalService.external.client;


import com.example.RentalService.external.request.PaymentRequest;
import com.example.RentalService.external.response.PaymentInfoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "PAYMENT-SERVICE", path = "/payment")
public interface PaymentService {

    @PostMapping
    ResponseEntity<Void> doPayment(@RequestBody PaymentRequest paymentRequest);

    @GetMapping("/info")
    ResponseEntity<PaymentInfoResponse> getPaymentInfo(
            @RequestParam Integer referenceId,
            @RequestParam String transactionType);

}
