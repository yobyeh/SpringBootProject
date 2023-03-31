package com.Book.App.BookApp.Repository;

import com.Book.App.BookApp.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<Book,Integer> {
}
