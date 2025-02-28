package com.henbran.fraud_detection.controller;

import com.henbran.fraud_detection.config.JwtAuthenticationFilter;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public AuthController(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username) throws MissingServletRequestParameterException {
        // Aqui verificamos se o username está vazio e retornamos 401
        if (username.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("O parâmetro 'username' é obrigatório.");
        }
        String token = jwtAuthenticationFilter.generateToken(username);
        return ResponseEntity.ok(token);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<String> handleMissingParams(MissingServletRequestParameterException ex) {
        String missingParam = ex.getParameterName();
        String message = "O parâmetro '" + missingParam + "' é obrigatório.";

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(message);
    }
}