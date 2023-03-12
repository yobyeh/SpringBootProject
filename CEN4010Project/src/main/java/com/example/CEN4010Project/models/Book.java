package com.example.CEN4010Project.models;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.Column;
//import javax.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

@Entity
@Table(name="BOOKS")
public class Book {

    @Id
    @GeneratedValue
    private Integer id;

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


    @Column(name="BOOK_NAME")
    private String bookName;

    public String getBookName() {
        return this.bookName;
    }

    public void setBookName(String bookName){
        this.bookName = bookName;
    }

    @Column(name="BOOK_DESCRIPTION")
    private String bookDescription;

    public String getBookDescription() {
        return this.bookDescription;
    }

    public void setBookDescription(String bookDescription){
        this.bookDescription = bookDescription;
    }

    @Column(name="PRICE")
    private Double price;

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price){
        this.price = price;
    }

    @Column(name="AUTHOR")
    private String author;

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author){
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

    @Column(name="YEAR")
    private Integer yearPublished;

    public Integer getYearPublished() {
        return this.yearPublished;
    }

    public void setYearPublished(Integer yearPublished){
        this.yearPublished = yearPublished;
    }

    @Column(name="COPIES_SOLD")
    private Integer copiesSold;

    public Integer getCopiesSold() {
        return this.copiesSold;
    }

    public void setCopiesSold(Integer copiesSold){
        this.copiesSold = copiesSold;
    }
}
