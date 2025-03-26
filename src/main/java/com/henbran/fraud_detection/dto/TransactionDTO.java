package com.henbran.fraud_detection.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionDTO (Long id,
    Integer step,
    String transactionType,
    BigDecimal amount,
    String initiator,
    String recipient,
    boolean isFraud,
    String location,
    LocalDateTime transactionDate,
    String currency){}
