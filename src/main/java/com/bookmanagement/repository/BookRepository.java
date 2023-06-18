package com.bookmanagement.repository;

import com.bookmanagement.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, Long> {
    List<Book> findAllBooksByBookName(String bookName);
}
