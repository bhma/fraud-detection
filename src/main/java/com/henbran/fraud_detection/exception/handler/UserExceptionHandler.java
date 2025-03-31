package com.henbran.fraud_detection.exception.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.henbran.fraud_detection.exception.InvalidDataUserException;

import lombok.extern.slf4j.Slf4j;

/**
 * Represents an exception related to user input or actions.
 */
@Slf4j
@RestControllerAdvice
public class UserExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        log.error("UserController -- Argumento ou parâmetro inválido: {}", ex.getMessage());
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(InvalidDataUserException.class)
    public ResponseEntity<String> handleException(Exception ex){
        log.error("UserController -- Dados do usuário inválidos: {}", ex.getMessage());
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
