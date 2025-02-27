package com.henbran.fraud_detection.controller;

import org.springframework.web.bind.annotation.RestController;
import com.henbran.fraud_detection.entity.Transaction;
import com.henbran.fraud_detection.service.FraudDetectionService;
import com.henbran.fraud_detection.service.TransactionService;
import lombok.RequiredArgsConstructor;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/transactions")
// @RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;
    private final FraudDetectionService fraudDetectionService;

    public TransactionController(TransactionService transactionService, FraudDetectionService fraudDetectionService) {
        this.transactionService = transactionService;
        this.fraudDetectionService = fraudDetectionService;
    }

    @GetMapping
    public ResponseEntity<Page<Transaction>> getAllTransactions(Pageable pageable) {
        Page<Transaction> transactions = transactionService.getAllTransactions(pageable);
        if (transactions.isEmpty()) {
            return ResponseEntity.noContent().build(); // Retorna 204 No Found se não encontrar nada
        }
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) {
        Transaction transaction = transactionService.getTransactionById(id);
        if(transaction == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(transaction);
    }

    @GetMapping("/filter")
    public ResponseEntity<Page<Transaction>> filterTransactions(
            Pageable pageable,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) Double amount) {
        Page<Transaction> filteredTransactions = transactionService.filterTransactions(pageable, location, amount);

        if (filteredTransactions.isEmpty()) {
            return ResponseEntity.noContent().build(); // Retorna 204 No Content se não encontrar nada

        }
        return ResponseEntity.ok(filteredTransactions);
    }

    @PostMapping
    public ResponseEntity<Transaction> saveTransaction(@RequestBody Transaction transaction) {
        Transaction transactionCreated = transactionService.saveTransaction(transaction);
        return ResponseEntity.ok(transactionCreated);
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
    }

    @PostMapping("/check-fraud")
    public ResponseEntity<String> checkFraud(@RequestBody Transaction transaction) {
        boolean isFraudulent = fraudDetectionService.isFraudulent(transaction);
        
        if (isFraudulent) {
            return ResponseEntity.ok("🚨 Fraude detectada na transação!");
        } else {
            return ResponseEntity.ok("✅ Transação segura.");
        }
    }
    

}
