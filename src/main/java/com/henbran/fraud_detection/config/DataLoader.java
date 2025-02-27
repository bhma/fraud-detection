package com.henbran.fraud_detection.config;

import com.henbran.fraud_detection.entity.Transaction;
import com.henbran.fraud_detection.repository.TransactionRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

// @Component
// @RequiredArgsConstructor
public class DataLoader {

    // private final TransactionRepository transactionRepository;

    @PostConstruct
    public void loadData() {
        // if (transactionRepository.count() == 0) { // Evita duplicações ao reiniciar
        //     Transaction t1 = new Transaction(Long.valueOf(1), BigDecimal.valueOf(250.75), "USD",
        //             LocalDateTime.parse("2025-02-03T12:30:00"), "New York", "COMPLETED", false);
        //     Transaction t7 = new Transaction(Long.valueOf(1), BigDecimal.valueOf(350.80), "USD",
        //             LocalDateTime.parse("2025-03-14T10:15:00"), "Los Angeles", "PENDING", false);

        //     Transaction t2 = new Transaction(Long.valueOf(2), BigDecimal.valueOf(127.50), "EUR",
        //             LocalDateTime.parse("2025-04-20T14:30:00"), "Berlin", "COMPLETED", true);

        //     Transaction t3 = new Transaction(Long.valueOf(3), BigDecimal.valueOf(210.00), "BRL",
        //             LocalDateTime.parse("2025-05-10T09:45:00"), "São Paulo", "FAILED", false);

        //     Transaction t4 = new Transaction(Long.valueOf(4), BigDecimal.valueOf(480.15), "GBP",
        //             LocalDateTime.parse("2025-06-05T16:20:00"), "London", "COMPLETED", true);

        //     Transaction t5 = new Transaction(Long.valueOf(5), BigDecimal.valueOf(332.40), "JPY",
        //             LocalDateTime.parse("2025-07-12T11:10:00"), "Tokyo", "PENDING", false);

        //     Transaction t6 = new Transaction(Long.valueOf(6), BigDecimal.valueOf(410.95), "AUD",
        //             LocalDateTime.parse("2025-08-01T13:55:00"), "Sydney", "COMPLETED", true);
        //     Transaction t8 = new Transaction(Long.valueOf(1), BigDecimal.valueOf(250.75), "USD",
        //             LocalDateTime.parse("2025-02-13T12:30:00"), "New York", "COMPLETED", false);
        //     Transaction t9 = new Transaction(Long.valueOf(1), BigDecimal.valueOf(250.75), "USD",
        //             LocalDateTime.parse("2025-02-13T12:30:00"), "New York", "COMPLETED", false);

        //     transactionRepository.saveAll(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9));
        //     System.out.println("📌 Dados inseridos com sucesso!");
        // }
    }
}