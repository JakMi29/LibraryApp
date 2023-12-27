package com.example.RentalService.external.response;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RentalInfoResponse {
    private Integer bookId;
    private OffsetDateTime receivedDate;
    private OffsetDateTime returnDate;
    private String email;
    private Long loanPeriod;
    private BigDecimal fee;
}
