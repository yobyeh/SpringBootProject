package com.example.CEN4010Project.Repository;

import com.example.CEN4010Project.models.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {

}
