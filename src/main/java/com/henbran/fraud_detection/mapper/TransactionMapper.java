package com.henbran.fraud_detection.mapper;

import com.henbran.fraud_detection.dto.TransactionDTO;
import com.henbran.fraud_detection.entity.Transaction;

public class TransactionMapper {
    
    public static TransactionDTO toDTO(Transaction transaction){
        if(transaction == null) return null;

        TransactionDTO transactionDTO = new TransactionDTO(transaction.getId(), transaction.getStep(), transaction.getTransactionType(), transaction.getAmount(), transaction.getInitiator(), transaction.getRecipient(), transaction.isFraud(), transaction.getLocation(), transaction.getTransactionDate(), transaction.getCurrency());

        return transactionDTO;
    }

    public static Transaction toEntity(TransactionDTO transactionDTO){
        if(transactionDTO == null) return null;

        Transaction transaction = new Transaction();
        transaction.setId(transactionDTO.id());
        transaction.setAmount(transactionDTO.amount());
        transaction.setCurrency(transactionDTO.currency());
        transaction.setFraud(transactionDTO.isFraud());
        transaction.setInitiator(transactionDTO.initiator());
        transaction.setLocation(transactionDTO.location());
        transaction.setRecipient(transactionDTO.recipient());
        transaction.setStep(transactionDTO.step());
        transaction.setTransactionDate(transactionDTO.transactionDate());
        transaction.setTransactionType(transactionDTO.transactionType());
        return transaction;
    }
}
