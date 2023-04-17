package com.example.CENproject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.CENproject.entity.CreditCard;

public interface CreditCardRepo extends JpaRepository<CreditCard, Integer> {

    Optional<CreditCard> findById(Integer id);
    
}
