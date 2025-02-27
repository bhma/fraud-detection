package com.henbran.fraud_detection.controller;

import com.henbran.fraud_detection.config.JwtAuthenticationFilter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public AuthController(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username) {
        String token = jwtAuthenticationFilter.generateToken(username);
        return ResponseEntity.ok(token);
    }
}