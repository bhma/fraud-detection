package com.henbran.fraud_detection.exception;

import com.henbran.fraud_detection.utils.Constants;

public class InvalidDataUserException extends RuntimeException {
    

    public InvalidDataUserException() {
        super(Constants.INVALID_DATA_USER_STRING);
    }
    
    public InvalidDataUserException(String message) {
        super(message);
    }

    public InvalidDataUserException(String message, Throwable cause) {
        super(message, cause);
    }
}
