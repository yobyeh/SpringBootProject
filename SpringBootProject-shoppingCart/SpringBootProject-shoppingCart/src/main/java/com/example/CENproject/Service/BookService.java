package com.example.CENproject.Service;

import com.example.CENproject.entity.Book;
import com.example.CENproject.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private BookRepo bookRepo;

    public Book saveDetails(Book book){
        return bookRepo.save(book);
    }

    public Book getBookDetailById(int id){
        return bookRepo.findById(id).orElse(null);
    }
    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }
    public List<String> getAllBooksTitle() {
        List<Book> books=bookRepo.findAll();
        List<String> titles = books.stream().map(Book::getBookName).collect(Collectors.toList());
        return titles;
    }

}
