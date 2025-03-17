package com.henbran.fraud_detection.dto;

import java.util.List;
import java.util.Set;

import com.henbran.fraud_detection.entity.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationDTO {
    
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private String city;
    private String username;
    private String password;
    private boolean isActive;
    private Set<Role> roles;
}
