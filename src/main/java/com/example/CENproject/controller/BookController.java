package com.example.CENproject.controller;
import com.example.CENproject.entity.Book;
import com.example.CENproject.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping("/addBook")
    public Book postDetail(@RequestBody Book book){
        return bookService.saveDetails(book);
    }
    @GetMapping("/getBookById/{id}")
    public Book fetchDetailById(@PathVariable int id){
        return bookService.getBookDetailById(id);
    }

    @GetMapping("/Books")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/BooksByTitle")
    public List<String> getAllBooksTitle() {
        return bookService.getAllBooksTitle();
    }
}