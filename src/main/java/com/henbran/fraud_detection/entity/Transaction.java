package com.henbran.fraud_detection.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
@Getter
@Setter
@RequiredArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer step;

    @Column(name = "transaction_type", length = 50)
    private String transactionType;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;

    @Column(length = 100)
    private String initiator;

    @Column(name = "old_bal_initiator", precision = 15, scale = 2)
    private BigDecimal oldBalInitiator;

    @Column(name = "new_bal_initiator", precision = 15, scale = 2)
    private BigDecimal newBalInitiator;

    @Column(length = 100)
    private String recipient;

    @Column(name = "old_bal_recipient", precision = 15, scale = 2)
    private BigDecimal oldBalRecipient;

    @Column(name = "new_bal_recipient", precision = 15, scale = 2)
    private BigDecimal newBalRecipient;

    @Column(name = "is_fraud")
    private boolean isFraud;

    @Column(length = 255)
    private String location;

    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;

    @Column(length = 3)
    private String currency;

    @Column(name = "is_active", nullable = false, columnDefinition = "boolean default true")
    private boolean isActive = true;

    // Construtor com todos os campos (exceto id)
    public Transaction(Integer step, String transactionType, BigDecimal amount,
            String initiator, BigDecimal oldBalInitiator, BigDecimal newBalInitiator,
            String recipient, BigDecimal oldBalRecipient, BigDecimal newBalRecipient,
            Boolean isFraud, String location, LocalDateTime transactionDate, String currency, boolean isActive) {
        this.step = step;
        this.transactionType = transactionType;
        this.amount = amount;
        this.initiator = initiator;
        this.oldBalInitiator = oldBalInitiator;
        this.newBalInitiator = newBalInitiator;
        this.recipient = recipient;
        this.oldBalRecipient = oldBalRecipient;
        this.newBalRecipient = newBalRecipient;
        this.isFraud = isFraud;
        this.location = location;
        this.transactionDate = transactionDate;
        this.currency = currency;
        this.isActive = isActive;
    }

    // Getters e Setters...
}
