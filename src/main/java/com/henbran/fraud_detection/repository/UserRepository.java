package com.henbran.fraud_detection.repository;

import org.springframework.stereotype.Repository;

import com.henbran.fraud_detection.entity.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Find a user by username
    @Query("SELECT u FROM User u WHERE u.username = :username")
    List<User> findByUsername(String username);
    
}
