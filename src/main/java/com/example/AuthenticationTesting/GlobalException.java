package com.example.AuthenticationTesting;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handlerException(Exception ex){
        return ResponseEntity.ok(ex.getLocalizedMessage());
    }


}
