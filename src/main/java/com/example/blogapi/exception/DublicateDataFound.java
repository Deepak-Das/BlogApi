package com.example.blogapi.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DublicateDataFound extends RuntimeException {
    private String message,error_status;

    public DublicateDataFound(String message, String error_status) {
        this.message = message;
        this.error_status = error_status;
    }
}
