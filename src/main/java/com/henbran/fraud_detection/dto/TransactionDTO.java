package com.henbran.fraud_detection.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionDTO {
    
    private Long id;
    private Integer step;
    private String transactionType;
    private BigDecimal amount;
    private String initiator;
    private String recipient;
    private boolean isFraud;
    private String location;
    private LocalDateTime transactionDate;
    private String currency;
    
}
