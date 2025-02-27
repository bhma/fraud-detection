package com.henbran.fraud_detection.repository;


import com.henbran.fraud_detection.entity.Transaction;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Page<Transaction> findAll(Pageable pageable);

    @Query("SELECT t FROM Transaction t WHERE (t.location = :location OR :location = '') AND (t.amount <= :amount OR :amount IS NULL)")
    Page<Transaction> findByLocationAndAmount(Pageable pageable, @Param("location") String location, @Param("amount") Double amount);

    @Query("SELECT t FROM Transaction t WHERE t.initiator = :initiator ORDER BY t.amount ASC")
    List<Transaction> findByInitiator(@Param("initiator") String initiator);

    @Query("SELECT COUNT(t) FROM Transaction t WHERE t.initiator = :initiator AND t.transactionDate > :transactionDate")
    Long countTransactionsByInitiatorAndTransactionDate(@Param("initiator") String initiator, @Param("transactionDate") LocalDateTime transactionDate);
}

