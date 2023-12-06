package com.example.BookService.domain.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BookServiceCustomException.class)
    public ResponseEntity<ErrorResponse> handleBookServiceException(BookServiceCustomException exception) {
        return new ResponseEntity<>(ErrorResponse.builder()
                .errorMessage(exception.getMessage())
                .errorCode(exception.getErrorCode())
                .build(), determineHttpStatus(exception.getErrorCode()));
    }
    private HttpStatus determineHttpStatus(String errorCode) {
        return switch (errorCode) {
            case "BOOK_NOT_AVAILABLE" -> HttpStatus.NOT_FOUND;
            case "SOME_OTHER_ERROR_CODE" -> HttpStatus.BAD_REQUEST;
            default -> HttpStatus.INTERNAL_SERVER_ERROR;
        };
    }
}
