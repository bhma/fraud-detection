package com.henbran.fraud_detection.exception.handler;

import java.security.InvalidAlgorithmParameterException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestControllerAdvice
public class AuthExceptionHandler {
    
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<String> handleMissingParams(MissingServletRequestParameterException ex) {
        String missingParam = ex.getParameterName();
        String message = "O parâmetro '" + missingParam + "' é obrigatório.";

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(message);
    }

    @ExceptionHandler(InvalidAlgorithmParameterException.class)
    public ResponseEntity<String> handleInvalidCredentials(InvalidAlgorithmParameterException ex){
        String message = ex.getMessage();
        log.warn("Úsuario ou senha inválidos");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
    }
}
