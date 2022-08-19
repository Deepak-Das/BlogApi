package com.example.blogapi.exception;

import com.example.blogapi.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> responseNotFoundException(ResourceNotFoundException ex){
        String msg=ex.getMessage();
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setMessage(msg);
        apiResponse.setError_status("404 Not Found");
        return new  ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> methodArgumentNotValidException(MethodArgumentNotValidException ex){
        Map<String,String> apiResponse = new HashMap<>();
        ex.getAllErrors().stream().forEach(error ->
               apiResponse.put(((FieldError) error).getField(), error.getDefaultMessage())
        );

        return new  ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }


}