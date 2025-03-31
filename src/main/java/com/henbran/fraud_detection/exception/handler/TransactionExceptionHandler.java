package com.henbran.fraud_detection.exception.handler;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

/**
 * Represents an exception that occurred during a transaction.
 */
@Slf4j
@RestControllerAdvice
public class TransactionExceptionHandler {
    
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        log.error("TransactionController -- IllegalArgumentException: " + ex.getMessage());
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

}
