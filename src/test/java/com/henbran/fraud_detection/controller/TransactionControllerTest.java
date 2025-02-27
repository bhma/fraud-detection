package com.henbran.fraud_detection.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.henbran.fraud_detection.entity.Transaction;
import com.henbran.fraud_detection.service.TransactionService;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TransactionService transactionService;

    @InjectMocks
    private TransactionController transactionController;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
    }

    @Test
    void shouldReturnOkWhenGetAllTransactions() throws Exception {
        // Cria uma lista de transações para simular o repositório
        List<Transaction> transactions = Arrays.asList(
                new Transaction(0, "TRANSFER", new BigDecimal(100), "Initiator", new BigDecimal(1000),
                        new BigDecimal(900), "Recipient", new BigDecimal(500), new BigDecimal(600), Boolean.FALSE, "New York", LocalDateTime.now(), "USD", true),
                new Transaction(0, "TRANSFER", new BigDecimal(100), "Initiator", new BigDecimal(1000),
                        new BigDecimal(900), "Recipient", new BigDecimal(500), new BigDecimal(600), Boolean.FALSE, "New York", LocalDateTime.now(), "USD", true),
                new Transaction(0, "TRANSFER", new BigDecimal(100), "Initiator", new BigDecimal(1000),
                        new BigDecimal(900), "Recipient", new BigDecimal(500), new BigDecimal(600), Boolean.TRUE, "New York", LocalDateTime.now(), "USD", true));

        Page<Transaction> PagedResponse = new PageImpl<>(transactions);
        // Configura o mock para retornar a lista de transações
        when(transactionService.getAllTransactions(any(Pageable.class))).thenReturn(PagedResponse);

        // Realiza uma requisição GET para /transactions
        mockMvc.perform(get("/transactions"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content", hasSize(greaterThan(1))))
                .andExpect(jsonPath("$.content[0].transactionType").value("TRANSFER"));
    }

    @Test
    public void shouldReturnNotFoundWhenNoTransactionsExist() throws Exception {
        // Implementação do teste para getAllTransactions quando não há transações
        when(transactionService.getAllTransactions(any(Pageable.class))).thenReturn(Page.empty());
        mockMvc.perform(get("/transactions"))
                .andExpect(status().isNoContent());
    }

}
