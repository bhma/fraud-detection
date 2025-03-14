package com.henbran.fraud_detection.exception;

import com.henbran.fraud_detection.utils.Constants;

public class InvalidDataTransactionException extends RuntimeException {
    
    public InvalidDataTransactionException() {
        super(Constants.INVALID_DATA_TRANSACTION_STRING);
    }
    public InvalidDataTransactionException(String message) {
        super(message);
    }
    
    public InvalidDataTransactionException(String message, Throwable cause) {
        super(message, cause);
    }


}
