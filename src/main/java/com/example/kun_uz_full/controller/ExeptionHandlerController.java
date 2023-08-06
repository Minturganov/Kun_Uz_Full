package com.example.kun_uz_full.controller;

import com.example.kun_uz_full.exp.AppBadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExeptionHandlerController {

    @ExceptionHandler(AppBadRequestException.class)
    public ResponseEntity<String> handler(AppBadRequestException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}
