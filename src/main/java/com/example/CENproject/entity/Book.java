package com.example.CENproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="Book_DB")
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @Column(name="ID")
    @GeneratedValue
    private int id;

    @Column(name="ISBN")
    private String isbn;
    @Column(name="BOOK_TITLE")
    private String bookName;
    @Column(name="DESCRIPTION")
    private String bookDescription;
    @Column(name="PRICE")
    private double price;
    @Column(name="AUTHOR")
    private String author;

    @Column(name="GENRE")
    private String genre;

    @Column(name="PUBLISHER")
    private String publisher;

    @Column(name="YEAR_PUBLISHED")
    private int yearPublished;

    @Column(name="COPIES_SOLD")
    private int copiesSold;

    @Column(name="AMOUNT")
    private int qty;
}
