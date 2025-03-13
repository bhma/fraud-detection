package com.henbran.fraud_detection.exception;

public class InvalidDataUserException extends RuntimeException {
    
    public InvalidDataUserException(String message) {
        super(message);
    }

    public InvalidDataUserException(String message, Throwable cause) {
        super(message, cause);
    }
}
