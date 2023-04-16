package com.example.CENproject.repository;

import com.example.CENproject.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.CENproject.entity.Author;
import java.util.List;

public interface BookRepo extends JpaRepository<Book,Integer> {
  
  List<Book> findByAuthor(Author author);
  
  Optional<Book> findByIsbn(String isbn);
}
