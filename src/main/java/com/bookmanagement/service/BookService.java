package com.bookmanagement.service;

import com.bookmanagement.exception.BookAlreadyExistsException;
import com.bookmanagement.exception.BookNotFoundException;
import com.bookmanagement.model.Book;
import com.bookmanagement.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    //2.
    public List<Book> getBooks(String name){
        return bookRepository.findAll();
    }

    //4.
    public Book getBookById(Long id){
        return bookRepository.findById(id)
                .orElseThrow(()-> new BookNotFoundException("Book not found with id :"+ id));
    }

    //6.
    public Book createBook(Book book) {
        if(bookRepository.existsById(book.getId())){
            throw new BookAlreadyExistsException("Book is already exists with id : " + book.getId());
        }
        return bookRepository.save(book);
    }

    //8.
    public void deleteBook(Long id) {
        Book foundBook= getBookById(id);
        bookRepository.delete(foundBook);
    }

    //10.
    public void updateBook(Long id, Book newBook) {
        Book foundBook = getBookById(id);
        foundBook.setBookName(newBook.getBookName());
        foundBook.setAuthor(newBook.getAuthor());
        foundBook.setPublicationDate(newBook.getPublicationDate());
        foundBook.setPrinting(newBook.getPrinting());
        bookRepository.save(foundBook);
    }

    //12.
    public Page<Book> getAllBooksPaging(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    //14
    public List<Book> getAllBooksByBookName(String bookName) {
        return bookRepository.findAllBooksByBookName(bookName);
    }

}
