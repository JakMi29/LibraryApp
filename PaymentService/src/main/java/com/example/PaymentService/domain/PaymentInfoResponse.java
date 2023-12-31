package com.example.PaymentService.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
@Data
@AllArgsConstructor
@Builder
public class PaymentInfoResponse {
    private String paymentMode;
    private String amount;
    private OffsetDateTime time;
}
