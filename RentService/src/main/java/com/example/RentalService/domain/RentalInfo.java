package com.example.RentalService.domain;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class RentalInfo {
    long period;
    BigDecimal fee;
}
