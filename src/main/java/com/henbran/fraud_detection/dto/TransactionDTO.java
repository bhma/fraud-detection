package com.henbran.fraud_detection.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Integer getStep() {
        return step;
    }
    public void setStep(Integer step) {
        this.step = step;
    }
    public String getTransactionType() {
        return transactionType;
    }
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public String getInitiator() {
        return initiator;
    }
    public void setInitiator(String initiator) {
        this.initiator = initiator;
    }
    public String getRecipient() {
        return recipient;
    }
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }
    public boolean isFraud() {
        return isFraud;
    }
    public void setFraud(boolean isFraud) {
        this.isFraud = isFraud;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }
    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    
}
