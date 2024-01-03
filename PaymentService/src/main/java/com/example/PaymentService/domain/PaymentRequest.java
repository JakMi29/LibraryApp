package com.example.PaymentService.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentRequest {

    private String email;
    private Integer referenceId;
    private String paymentMode;
    private BigDecimal amount;

}
