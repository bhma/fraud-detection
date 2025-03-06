package com.henbran.fraud_detection.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.henbran.fraud_detection.entity.User;
import com.henbran.fraud_detection.repository.UserRepository;

@Service
public class UserService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User saveUser(User user) {
        // Check if the username is already taken
        if (!userRepository.findByUsername(user.getUsername()).isEmpty()) {
            throw new IllegalArgumentException("Username already taken");
        }

        // Encode the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Save the user to the database
        return userRepository.save(user);
    }
}
