package com.henbran.fraud_detection.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.henbran.fraud_detection.entity.Transaction;
import com.henbran.fraud_detection.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Cacheable(value = "transactions", key = "#pageable.pageNumber")
    public Page<Transaction> getAllTransactions(Pageable pageable) {
        return transactionRepository.findAll(pageable);
    }

    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id).orElse(null);
    }

    @CacheEvict(value = "transactions", allEntries = true)
    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @CacheEvict(value = "transactions", allEntries = true)
    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }

    public Page<Transaction> filterTransactions(Pageable pageable, String location, Double amount) {
        return transactionRepository.findByLocationAndAmount(pageable, location == null ? "" : location, amount);
    }

    public boolean isTransactionValid(Transaction transaction){
        return 
            transaction.getInitiator() != null && 
            transaction.getRecipient() != null && 
            transaction.getAmount() != BigDecimal.ZERO && 
            transaction.getLocation() != null &&
            transaction.getTransactionDate() != null &&
            transaction.getTransactionType() != null;
    }

    
}
