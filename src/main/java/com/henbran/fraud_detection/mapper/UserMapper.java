package com.henbran.fraud_detection.mapper;

import com.henbran.fraud_detection.dto.UserDTO;
import com.henbran.fraud_detection.dto.UserRegistrationDTO;
import com.henbran.fraud_detection.entity.User;

public class UserMapper {
    
    public static UserDTO toDTO(User user){

        if(user == null) return null;

        UserDTO dto = new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhoneNumber(), user.getAddress(), user.getCity(), user.getUsername());
        return dto;
    }

    public static User toEntity(UserDTO dto){
        if(dto == null) return null;

        User user = new User();
        user.setId(dto.id());
        user.setFirstName(dto.firstName());
        user.setLastName(dto.lastName());
        user.setEmail(dto.email());
        user.setPhoneNumber(dto.phoneNumber());
        user.setAddress(dto.address());
        user.setCity(dto.city());
        user.setUsername(dto.username());
        return user;
    }
    public static User fromRegistrationToEntity(UserRegistrationDTO userRegistrationDTO) {
        if (userRegistrationDTO == null) {
            return null;
        }
        User user = new User();
        user.setId(userRegistrationDTO.id());
        user.setFirstName(userRegistrationDTO.firstName());
        user.setLastName(userRegistrationDTO.lastName());
        user.setEmail(userRegistrationDTO.email());
        user.setPhoneNumber(userRegistrationDTO.phoneNumber());
        user.setAddress(userRegistrationDTO.address());
        user.setCity(userRegistrationDTO.city());
        user.setUsername(userRegistrationDTO.username());
        user.setPassword(userRegistrationDTO.password());
        user.setActive(userRegistrationDTO.isActive());
        return user;
    }
}
