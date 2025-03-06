package com.henbran.fraud_detection.repository;

import org.springframework.stereotype.Repository;

import com.henbran.fraud_detection.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
}
