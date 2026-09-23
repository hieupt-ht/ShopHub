package com.example.shophub.common;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.shophub.common.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<Map<String, String>> handlerValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(e -> {
            errors.put(e.getField(), e.getDefaultMessage());
        });
        return new ApiResponse<>(999, "List errors", errors);
    }

    @ExceptionHandler(BadCredentialsException.class)
    // public ResponseEntity<ApiResponse<String>>
    // handlerBadCredentialException(BadCredentialsException ex){
    // return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
    // .body(new ApiResponse<>(401, "Invalid user or password", null));
    // }
    public ResponseEntity<ApiResponse<String>> handlerBadCredentialException(BadCredentialsException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ApiResponse<>(401, "Invalid user or password", null));
    }

    @ExceptionHandler(InvalidRefreshTokenException.class)
    public ApiResponse<String> handlerInvalidRefreshTokenException(InvalidRefreshTokenException ex) {
        return new ApiResponse<>(401, ex.getMessage(), null);
    }

    @ExceptionHandler(ObjectIsNotFoundException.class)
    public ApiResponse<String> handleObjectIsNotFoundException(ObjectIsNotFoundException ex) {
        return new ApiResponse<>(404, ex.getMessage(), null);
    }
}
