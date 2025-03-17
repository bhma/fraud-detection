package com.henbran.fraud_detection.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.henbran.fraud_detection.entity.Role;
import com.henbran.fraud_detection.repository.RoleRepository;

@Service
public class RoleService {
    
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    public Role getRoleById(Long roleId) {
        return roleRepository.findById(roleId).orElse(null);
    }

    public List<Role> getAllRoles(){
        return roleRepository.findAll();
    }
}
