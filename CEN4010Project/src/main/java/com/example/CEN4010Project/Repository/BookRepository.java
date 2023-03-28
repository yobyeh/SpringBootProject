package com.example.CEN4010Project.Repository;

import com.example.CEN4010Project.models.Author;
import com.example.CEN4010Project.models.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {

    List<Book> findByAuthor(Author author);

}
