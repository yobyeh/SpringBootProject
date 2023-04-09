package com.example.CENproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="shoppingdb")
public class ShoppingCart {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "book_id")
    private int bookId;

    @Column(name="price")
    private double price;
    @Column(name="userName")
    private String userName;


}