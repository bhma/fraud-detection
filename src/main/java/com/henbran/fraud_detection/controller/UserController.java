package com.henbran.fraud_detection.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.henbran.fraud_detection.entity.User;
import com.henbran.fraud_detection.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/user")
public class UserController {
    
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User userCreated = userService.saveUser(user);
        return ResponseEntity.ok(userCreated);
    }
    
}
