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
    //@Column(name="ID")
    @GeneratedValue
    private int id;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id){
        this.id = id;
    }


    @Column(name="ISBN")
    private String isbn;

    public String getISBN() {
        return this.isbn;
    }

    public void setISBN(String isbn){
        this.isbn = isbn;
    }

    @Column(name="BOOK_TITLE")
    private String bookName;

    public String getBookName() {
        return this.bookName;
    }

    public void setBookName(String bookName){
        this.bookName = bookName;
    }

    @Column(name="DESCRIPTION")
    private String bookDescription;

    public String getBookDescription() {
        return this.bookDescription;
    }

    public void setBookDescription(String bookDescription){
        this.bookDescription = bookDescription;
    }

    @Column(name="PRICE")
    private double price;

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price){
        this.price = price;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "AUTHORS_ID")
    private Author author;

    public Author getAuthor() {
        return this.author;
    }

    public void setAuthor(Author author){
        this.author = author;
    }

    @Column(name="GENRE")
    private String genre;

    public String getGenre() {
        return this.genre;
    }

    public void setGenre(String genre){
        this.genre = genre;
    }

    @Column(name="PUBLISHER")
    private String publisher;

    public String getPublisher() {
        return this.publisher;
    }

    public void setPublisher(String publisher){
        this.publisher = publisher;
    }

    @Column(name="YEAR_PUBLISHED")
    private int yearPublished;

    public Integer getYearPublished() {
        return this.yearPublished;
    }

    public void setYearPublished(Integer yearPublished){
        this.yearPublished = yearPublished;
    }


    @Column(name="COPIES_SOLD")
    private int copiesSold;

    public Integer getCopiesSold() {
        return this.copiesSold;
    }

    public void setCopiesSold(Integer copiesSold){
        this.copiesSold = copiesSold;
    }

    @Column(name="AMOUNT")
    private int quantities;

    public Integer getQuantities() {
        return this.quantities;
    }

    public void setQuantities(Integer quantities){
        this.quantities = quantities;
    }
}
