package com.example.BookService.domain.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class BookServiceCustomException extends RuntimeException {
    HttpStatus status;
    public BookServiceCustomException(String message, HttpStatus status) {
        super(message);
        this.status=status;
    }
}
