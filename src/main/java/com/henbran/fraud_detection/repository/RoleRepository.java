package com.henbran.fraud_detection.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.henbran.fraud_detection.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
