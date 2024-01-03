package com.example.RentalService.domain;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class RentalServiceCustomException extends RuntimeException {

    private HttpStatus status;

    public RentalServiceCustomException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
