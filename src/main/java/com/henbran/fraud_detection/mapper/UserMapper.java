package com.henbran.fraud_detection.mapper;

import com.henbran.fraud_detection.dto.UserDTO;
import com.henbran.fraud_detection.dto.UserRegistrationDTO;
import com.henbran.fraud_detection.entity.User;

public class UserMapper {
    
    public static UserDTO toDTO(User user){

        if(user == null) return null;

        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setAddress(user.getAddress());
        dto.setCity(user.getCity());
        dto.setUsername(user.getUsername());
        return dto;
    }

    public static User toEntity(UserDTO dto){
        if(dto == null) return null;

        User user = new User();
        user.setId(dto.getId());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setAddress(dto.getAddress());
        user.setCity(dto.getCity());
        user.setUsername(dto.getUsername());
        return user;
    }
    public static User fromRegistrationToEntity(UserRegistrationDTO userRegistrationDTO) {
        if (userRegistrationDTO == null) {
            return null;
        }
        User user = new User();
        user.setId(userRegistrationDTO.getId());
        user.setFirstName(userRegistrationDTO.getFirstName());
        user.setLastName(userRegistrationDTO.getLastName());
        user.setEmail(userRegistrationDTO.getEmail());
        user.setPhoneNumber(userRegistrationDTO.getPhoneNumber());
        user.setAddress(userRegistrationDTO.getAddress());
        user.setCity(userRegistrationDTO.getCity());
        user.setUsername(userRegistrationDTO.getUsername());
        user.setPassword(userRegistrationDTO.getPassword());
        user.setActive(userRegistrationDTO.isActive());
        return user;
    }
}
