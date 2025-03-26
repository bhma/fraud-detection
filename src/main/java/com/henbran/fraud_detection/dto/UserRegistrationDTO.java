package com.henbran.fraud_detection.dto;


public record UserRegistrationDTO(
    Long id,
    String firstName,
    String lastName,
    String email,
    String phoneNumber,
    String address,
    String city,
    String username,
    String password,
    boolean isActive) {}
