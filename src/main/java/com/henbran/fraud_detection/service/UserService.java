package com.henbran.fraud_detection.service;

import java.util.List;

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
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new IllegalArgumentException("Username already taken");
        }

        // Encode the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Save the user to the database
        return userRepository.save(user);
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public List<User> getAllusers(){
        return userRepository.findAll();
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public String deleteUser(Long userId){
        User deletingUser = userRepository.findById(userId).orElse(null);
        if(deletingUser == null){
            throw new IllegalArgumentException("User not found");
        }
        deletingUser.setActive(false);
        userRepository.save(deletingUser);
        return "User deleted successfully";
    }
}
