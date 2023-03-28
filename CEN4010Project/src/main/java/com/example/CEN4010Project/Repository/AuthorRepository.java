package com.example.CEN4010Project.Repository;

import com.example.CEN4010Project.models.Author;
import com.example.CEN4010Project.models.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface AuthorRepository extends CrudRepository<Author, Integer> {

}
