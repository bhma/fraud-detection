package com.henbran.fraud_detection.mapper;

import com.henbran.fraud_detection.dto.TransactionDTO;
import com.henbran.fraud_detection.entity.Transaction;

public class TransactionMapper {
    
    public static TransactionDTO toDTO(Transaction transaction){
        if(transaction == null) return null;

        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setId(transaction.getId());
        transactionDTO.setAmount(transaction.getAmount());
        transactionDTO.setCurrency(transaction.getCurrency());
        transactionDTO.setFraud(transaction.isFraud());
        transactionDTO.setInitiator(transaction.getInitiator());
        transactionDTO.setLocation(transaction.getLocation());
        transactionDTO.setRecipient(transaction.getRecipient());
        transactionDTO.setStep(transaction.getStep());
        transactionDTO.setTransactionDate(transaction.getTransactionDate());
        transactionDTO.setTransactionType(transaction.getTransactionType());

        return transactionDTO;
    }

    public static Transaction toEntity(TransactionDTO transactionDTO){
        if(transactionDTO == null) return null;

        Transaction transaction = new Transaction();
        transaction.setId(transactionDTO.getId());
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setCurrency(transactionDTO.getCurrency());
        transaction.setFraud(transactionDTO.isFraud());
        transaction.setInitiator(transactionDTO.getInitiator());
        transaction.setLocation(transactionDTO.getLocation());
        transaction.setRecipient(transactionDTO.getRecipient());
        transaction.setStep(transactionDTO.getStep());
        transaction.setTransactionDate(transactionDTO.getTransactionDate());
        transaction.setTransactionType(transactionDTO.getTransactionType());
        return transaction;
    }
}
