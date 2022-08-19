package com.example.blogapi.payload;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ApiResponse {
    String message;
    String error_status;
}
