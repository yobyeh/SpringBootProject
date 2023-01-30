package com.example.CEN4010Project.controller;

import java.lang.Iterable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.CEN4010Project.Repository.BookRepository;
import com.example.CEN4010Project.models.Book;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {
    private final BookRepository bookRepository;

    public BookController(final BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }


    @GetMapping("/Books")
    public Iterable<Book> getBook() {
        return this.bookRepository.findAll();
    }

    @GetMapping("/Books/{id}")
    public Optional<Book> getBookById(@PathVariable("id") Integer id) {
        return this.bookRepository.findById(id);
    }

    @PostMapping("/Books")
    public Book createNewBook(@RequestBody Book book){
        Book newBook = this.bookRepository.save(book);
        return newBook;
    }


}
