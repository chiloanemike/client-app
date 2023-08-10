package com.investec.clientapp.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ApiResponse {
    private HttpStatus status;
    private String message;
    private String error;

}
