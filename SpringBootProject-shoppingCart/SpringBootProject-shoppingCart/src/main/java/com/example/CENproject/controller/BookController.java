package com.example.CENproject.controller;
import com.example.CENproject.entity.Author;
import com.example.CENproject.entity.Book;
import com.example.CENproject.Service.BookService;
import com.example.CENproject.repository.AuthorRepository;
import com.example.CENproject.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepo bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public BookController(final BookRepo bookRepository){
        this.bookRepository = bookRepository;
    }

    @PostMapping("/Books")
    public Book createNewBook(@RequestBody Book book){
        Author author = authorRepository.findById(book.getAuthor().getId())
                .orElseThrow(() -> new BookNotFoundException("Author not found"));

        Book newBook = new Book();
        newBook.setISBN(book.getISBN());
        newBook.setBookName(book.getBookName());
        newBook.setBookDescription(book.getBookDescription());
        newBook.setPrice(book.getPrice());
        newBook.setAuthor(author);
        newBook.setGenre(book.getGenre());
        newBook.setPublisher(book.getPublisher());
        newBook.setYearPublished(book.getYearPublished());
        newBook.setCopiesSold(book.getCopiesSold());
        newBook.setQuantities(book.getQuantities());

        Book savedBook = this.bookRepository.save(newBook);
        return savedBook;
    }


    @GetMapping("/Books")
    public Iterable<Book> getBook() {
        return this.bookRepository.findAll();
    }

    @GetMapping("/Books/{id}")
    public Optional<Book> getBookById(@PathVariable("id") Integer id) {
        return this.bookRepository.findById(id);
    }

    @GetMapping("/BooksByTitle")
    public List<String> getAllBooksTitle() {
        return bookService.getAllBooksTitle();
    }
}
