package com.henbran.fraud_detection.controller;

import java.security.InvalidAlgorithmParameterException;
import java.util.List;

import com.henbran.fraud_detection.config.JwtAuthenticationFilter;
import com.henbran.fraud_detection.service.UserService;
import com.henbran.fraud_detection.utils.Constants;

import lombok.extern.slf4j.Slf4j;

import com.henbran.fraud_detection.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    
    public AuthController(JwtAuthenticationFilter jwtAuthenticationFilter, UserService userService, PasswordEncoder passwordEncoder) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) throws MissingServletRequestParameterException, InvalidAlgorithmParameterException {
        
        log.info("Iniciando login do úsuario: {}", username);
        if(!checkUsernameAndPassword(username, password)){
            throw new InvalidAlgorithmParameterException(Constants.INVALID_USER_OR_PASSWORD_STRING);
        }
        log.info("Login realizado com sucesso");
        String token = jwtAuthenticationFilter.generateToken(username);
        return ResponseEntity.ok(token);
    }

    private boolean checkUsernameAndPassword(String username, String password) {
        User user = userService.getUserByUsername(username);
        if (user == null) {
            return false;
        }
        return passwordEncoder.matches(password, user.getPassword()) && user.getUsername().equals(username);
    }
}