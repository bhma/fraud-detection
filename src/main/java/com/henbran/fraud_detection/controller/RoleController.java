package com.henbran.fraud_detection.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.henbran.fraud_detection.entity.Role;
import com.henbran.fraud_detection.service.RoleService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }
    
    @PostMapping("/register")
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        Role savedRole = roleService.saveRole(role);
        // System.out.println(role.toString());
        return ResponseEntity.ok(savedRole);
    }

    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleService.getAllRoles();
        return ResponseEntity.ok(roles);
    }
    
    

}
