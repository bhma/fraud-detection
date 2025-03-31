package com.henbran.fraud_detection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@EnableCaching
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class FraudDetectionApplication {

	public static void main(String[] args) {
        log.info("Iniciando API Fraud Detection!");
		SpringApplication.run(FraudDetectionApplication.class, args);
        log.info("API Fraud Detection iniciada com sucesso!");
	}

}
