package com.henbran.fraud_detection.service;

import com.henbran.fraud_detection.entity.Transaction;
import com.henbran.fraud_detection.repository.TransactionRepository;
import com.henbran.fraud_detection.utils.Constants;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FraudDetectionService {

    private final TransactionRepository transactionRepository;

    public FraudDetectionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Boolean isFraudulent(Transaction transaction) {
        log.info("Executando detecção de fraude para a transação: {}", transaction.getId());
        boolean isFraud = isHighAmount(transaction) ||
                          isHighFrequency(transaction) ||
                          isUnusualLocation(transaction) ||
                          isDifferentCurrency(transaction);

        if(isFraud){
            RedisPublisherService.publish(Constants.REDIS_CHANNEL_STRING, Constants.FRAUD_DETECTED_STRING);
        }
        return isHighAmount(transaction) ||
                isHighFrequency(transaction) ||
                isUnusualLocation(transaction) ||
                isDifferentCurrency(transaction);
    }

    private boolean isHighAmount(Transaction transaction) {
        List<Transaction> transactions = transactionRepository.findByInitiator(transaction.getInitiator());
        BigDecimal medianAmount = transactions.get(transactions.size() / 2).getAmount();
        BigDecimal maxMedianAmount = medianAmount.multiply(BigDecimal.valueOf(2));
        return transaction.getAmount().compareTo(maxMedianAmount) > 0;
    }

    private boolean isHighFrequency(Transaction transaction) {
        LocalDateTime fiveMinutesAgo = transaction.getTransactionDate().minusMinutes(5);
        Long recentTransactions = transactionRepository
                .countTransactionsByInitiatorAndTransactionDate(transaction.getInitiator(), fiveMinutesAgo);
        return recentTransactions >= 5;
    }

    private boolean isUnusualLocation(Transaction transaction) {
        List<Transaction> transactions = transactionRepository.findByInitiator(transaction.getInitiator());
        if (transactions.isEmpty())
            return false;

        Set<String> previousLocations = transactions.stream()
                .map(Transaction::getLocation)
                .collect(Collectors.toSet());
        return !previousLocations.contains(transaction.getLocation());
    }

    private boolean isDifferentCurrency(Transaction transaction) {
        List<Transaction> transactions = transactionRepository.findByInitiator(transaction.getInitiator());
        if (transactions.isEmpty())
            return false;

        Set<String> previousCurrencies = transactions.stream()
                .map(Transaction::getCurrency)
                .collect(Collectors.toSet());
        return !previousCurrencies.contains(transaction.getCurrency());
    }
}
