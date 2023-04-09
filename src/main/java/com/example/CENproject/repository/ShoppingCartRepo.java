package com.example.CENproject.repository;


import com.example.CENproject.entity.Book;
import com.example.CENproject.entity.ShoppingCart;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartRepo extends JpaRepository<ShoppingCart,Integer> {

    Optional<ShoppingCart> findByUserIdAndBookId(long userId, int bookId);
    List<ShoppingCart> findByUserId(long userId);
    Optional<ShoppingCart> findByBookId(long userId);



}
