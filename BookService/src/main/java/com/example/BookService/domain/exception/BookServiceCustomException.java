package com.example.BookService.domain.exception;

import lombok.Data;

@Data
public class BookServiceCustomException extends RuntimeException{

    private String errorCode;

    public BookServiceCustomException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
