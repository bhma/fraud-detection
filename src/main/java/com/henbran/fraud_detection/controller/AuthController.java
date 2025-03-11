package com.henbran.fraud_detection.controller;

import java.util.List;

import com.henbran.fraud_detection.config.JwtAuthenticationFilter;
import com.henbran.fraud_detection.service.UserService;
import com.henbran.fraud_detection.entity.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) throws MissingServletRequestParameterException {
        // Aqui verificamos se o username está vazio e retornamos 401
        if (username.isEmpty() && password.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Os parâmetros 'username' e 'password' são obrigatórios.");
        }
        if(!checkUsernameAndPassword(username, password)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha incorretos.");
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

    private boolean checkUsernameAndPassword(String username, String password) {
        User user = userService.getUserByUsername(username).get(0);
        if (user == null) {
            return false;
        }
        return passwordEncoder.matches(password, user.getPassword()) && user.getUsername().equals(username);
    }
}