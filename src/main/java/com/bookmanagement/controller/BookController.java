package com.bookmanagement.controller;

import com.bookmanagement.model.Book;
import com.bookmanagement.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    //1-get all books
    //http://localhost:8080/books + GET
    @GetMapping
    public ResponseEntity<List<Book>> getBooks(@RequestParam(required = false) String name ){
        return new ResponseEntity<>(bookService.getBooks(name), HttpStatus.OK);
    }

    //3-get book by id
    ////http://localhost:8080/books/1 + GET
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id){
        return new ResponseEntity<>(getBookById(id),HttpStatus.OK);
    }

    //5-create a new Book
    ////http://localhost:8080/books + POST
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book){

        return new ResponseEntity<>(bookService.createBook(book), HttpStatus.CREATED);
    }

    //7-delete Book by id
    //http://localhost:8080/books/1+DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);

        return new  ResponseEntity<>(HttpStatus.OK);
    }

    //9-update Book
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBook(@PathVariable("id") Long id, @RequestBody Book book){
        bookService.updateBook(id, book);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //11-pagination
    //http://localhost:8080/books/page?page=1&size=10&sort=name&direction=DESC + GET
    @GetMapping("/page")
    public ResponseEntity<Page<Book>> getAllBookByPage(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "2") int size,
            @RequestParam("sort") String prop,
            @RequestParam("direction") Sort.Direction direction){

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, prop));
        Page<Book> booksByPage = bookService.getAllBooksPaging(pageable);
        return  new ResponseEntity<>(booksByPage, HttpStatus.OK);
    }

    //13
    //http://localhost:8080/books/querybookname?bookName=  + GET
    @GetMapping("/querybookname")
    public ResponseEntity<List<Book>> getAllBooksByBookName(@RequestParam("bookName") String bookName){
        List<Book> bookList = bookService.getAllBooksByBookName(bookName);
        return new ResponseEntity<>(bookList, HttpStatus.OK);
    }

    //
    private Book getBookById(Long id){
        return bookService.getBookById(id);
    }




}
