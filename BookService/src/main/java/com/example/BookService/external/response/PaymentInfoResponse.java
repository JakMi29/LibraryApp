package com.example.BookService.external.response;

import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;
@Data
@Builder
public class PaymentInfoResponse {
    private String paymentMode;
    private String amount;
    private OffsetDateTime time;
}
