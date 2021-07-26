package com.mypricetracker.pricetracker.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> handleConflict(RuntimeException e, WebRequest webRequest) {
        String message = "NullPointerException occurred! Mandatory parameter missing";
        log.error(message);
        return handleExceptionInternal(e, message, new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);
    }


}