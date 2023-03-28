package com.example.CEN4010Project.controller;

import com.example.CEN4010Project.Repository.AuthorRepository;
import com.example.CEN4010Project.models.Author;
import com.example.CEN4010Project.models.Book;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AuthorController {
    private final AuthorRepository authorRepository;

    public AuthorController(final AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @PostMapping("/Authors")
    public Author createNewAuthor(@RequestBody Author author){
        Author newAuthor = this.authorRepository.save(author);
        return newAuthor;
    }


}
