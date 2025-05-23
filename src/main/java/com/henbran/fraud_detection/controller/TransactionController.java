package com.henbran.fraud_detection.controller;

import org.springframework.web.bind.annotation.RestController;

import com.henbran.fraud_detection.dto.TransactionDTO;
import com.henbran.fraud_detection.entity.Transaction;
import com.henbran.fraud_detection.exception.InvalidDataTransactionException;
import com.henbran.fraud_detection.mapper.TransactionMapper;
import com.henbran.fraud_detection.service.FraudDetectionService;
import com.henbran.fraud_detection.service.TransactionService;
import com.henbran.fraud_detection.utils.Constants;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.MissingFormatArgumentException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
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
    public ResponseEntity<Page<TransactionDTO>> getAllTransactions(Pageable pageable) {
        log.info("Buscando todas as transações");
        Page<Transaction> transactions = transactionService.getAllTransactions(pageable);
        if (transactions.isEmpty()) {
            log.info("Não há transações cadastradas");
            return ResponseEntity.noContent().build(); // Retorna 204 No Found se não encontrar nada
        }
        Page<TransactionDTO> transactionDTOList = transactions.map(TransactionMapper::toDTO);

        return ResponseEntity.ok(transactionDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDTO> getTransactionById(@PathVariable Long id) {
        log.info("Buscando transação com id: {}", id);
        Transaction transaction = transactionService.getTransactionById(id);
        if(transaction == null) {
            log.info("Transação não encontrada");
            return ResponseEntity.notFound().build();
        }
        TransactionDTO transactionDTO = TransactionMapper.toDTO(transaction);
        return ResponseEntity.ok(transactionDTO);
    }

    @GetMapping("/filter")
    public ResponseEntity<Page<TransactionDTO>> filterTransactions(
            Pageable pageable,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) Double amount) {
        Page<Transaction> filteredTransactions = transactionService.filterTransactions(pageable, location, amount);

        if (filteredTransactions.isEmpty()) {
            return ResponseEntity.noContent().build(); // Retorna 204 No Content se não encontrar nada

        }
        Page<TransactionDTO> filtredTransactionsDTO = filteredTransactions.map(TransactionMapper::toDTO);
        return ResponseEntity.ok(filtredTransactionsDTO);
    }

    @PostMapping
    public ResponseEntity<TransactionDTO> saveTransaction(@RequestBody Transaction transaction) {
        log.info("Salvando nova transação");
        // Verifica se o transaction é válido
        if (!transactionService.isTransactionValid(transaction)) {
            throw new InvalidDataTransactionException();
        }
        Transaction transactionCreated = transactionService.saveTransaction(transaction);
        TransactionDTO transactionCreatedDTO = TransactionMapper.toDTO(transactionCreated);
        log.info("Transação salva com sucesso");
        return ResponseEntity.ok(transactionCreatedDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable(required = true) Long id) {
        log.info("Deletando transação com id: {}", id);
        transactionService.deleteTransaction(id);
    }

    @PostMapping("/check-fraud")
    public ResponseEntity<String> checkFraud(@RequestBody Transaction transaction) {
        log.info("Verificando transação com id {}", transaction.getId());
        boolean isFraudulent = fraudDetectionService.isFraudulent(transaction);
        
        if (isFraudulent) {
            return ResponseEntity.ok(Constants.FRAUD_DETECTED_STRING);
        } else {
            return ResponseEntity.ok(Constants.SECURE_TRANSACTION_STRING);
        }
    }
    

}
