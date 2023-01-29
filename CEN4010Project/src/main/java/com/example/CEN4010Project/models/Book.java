package com.example.CEN4010Project.models;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;

@Entity
@Table(name="Books")
public class Book {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name="ISBN")
    private String isbn;

    @Column(name="BOOK_NAME")
    private String bookName;

    @Column(name="BOOK_DESCRIPTION")
    private String bookDescription;

    @Column(name="PRICE")
    private Double price;

    @Column(name="AUTHOR")
    private String author;

    @Column(name="GENRE")
    private String genre;

    @Column(name="PUBLISHER")
    private String publisher;

    @Column(name="YEAR_PUBLISHED")
    private Integer yearPublished;

    @Column(name="COPIES_SOLD")
    private Integer copiesSold;
}
