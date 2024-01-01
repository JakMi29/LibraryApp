package com.example.RentalService.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@Builder
public class FinishedRentalsResponse {
    String bookName;
    OffsetDateTime receivedDate;
    OffsetDateTime returnDate;
    Integer rentalPeriod;
    BigDecimal fee;
    String email;
    String paymentMode;
}
