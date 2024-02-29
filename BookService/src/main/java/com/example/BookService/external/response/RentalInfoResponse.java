package com.example.BookService.external.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@Builder
public class RentalInfoResponse {
    private Integer bookId;
    private OffsetDateTime receivedDate;
    private OffsetDateTime returnDate;
    private String email;
    private Integer loanPeriod;
    private BigDecimal fee;
}
