package com.example.CENproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.CENproject.entity.User;

public interface UserRepo extends JpaRepository<User, Long> {
    
}
