package com.henbran.fraud_detection.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.henbran.fraud_detection.dto.UserDTO;
import com.henbran.fraud_detection.dto.UserRegistrationDTO;
import com.henbran.fraud_detection.entity.User;
import com.henbran.fraud_detection.exception.InvalidDataUserException;
import com.henbran.fraud_detection.mapper.UserMapper;
import com.henbran.fraud_detection.service.UserService;
import com.henbran.fraud_detection.utils.Constants;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/users")
public class UserController {

    // private final AuthController authController;
    
    private final UserService userService;

    public UserController(UserService userService, AuthController authController) {
        this.userService = userService;
        // this.authController = authController;
    }
    
    @PostMapping("/register")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserRegistrationDTO userRegDTO) {
        User newUser = UserMapper.fromRegistrationToEntity(userRegDTO);
        if(!userService.isUserValid(newUser)){
            throw new InvalidDataUserException();
        }
        User userCreated = userService.saveUser(newUser);
        UserDTO createdUserDTO = UserMapper.toDTO(userCreated);
        return ResponseEntity.ok(createdUserDTO);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        List<User> users = userService.getAllusers();
        if(users.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        List<UserDTO> userDTOList = users.stream().map(UserMapper::toDTO).toList();
        return ResponseEntity.ok(userDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id){
        User user = userService.getUserById(id);
        if(user == null){
            return ResponseEntity.notFound().build();
        }
        UserDTO userDTO = UserMapper.toDTO(user);
        return ResponseEntity.ok(userDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        if(id == null){
            throw new IllegalArgumentException(Constants.ID_NOT_NULL_STRING);
        }
        String response = userService.deleteUser(id);
        return ResponseEntity.ok(response);
    }
    
}
