package com.example.OpinionService.domain.exception;

import lombok.Data;

@Data
public class OpinionServiceCustomException extends RuntimeException{

    private String errorCode;

    public OpinionServiceCustomException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
