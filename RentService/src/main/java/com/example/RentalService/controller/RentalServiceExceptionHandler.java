package com.example.RentalService.controller;

import com.example.RentalService.domain.RentalServiceCustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RentalServiceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RentalServiceCustomException.class)
    public ResponseEntity<String> handleCustomException(RentalServiceCustomException exception) {
        return new ResponseEntity<>(
                exception.getMessage(),
                exception.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleOtherException(Exception exception) {
        System.out.println(exception.getMessage());
        return new ResponseEntity<>(
                "Something gone wrong",
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
