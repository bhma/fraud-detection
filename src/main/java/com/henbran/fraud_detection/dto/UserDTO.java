package com.henbran.fraud_detection.dto;

public record UserDTO (
    Long id,
    String firstName,
    String lastName,
    String email,
    String phoneNumber,
    String address,
    String city,
    String username) {}
