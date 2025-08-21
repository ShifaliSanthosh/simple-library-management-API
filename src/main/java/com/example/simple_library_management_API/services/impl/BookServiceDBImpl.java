package com.example.simple_library_management_API.services.impl;

import com.example.simple_library_management_API.exceptions.ResourceNotFoundException;
import com.example.simple_library_management_API.models.Book;
import com.example.simple_library_management_API.repositories.BookRepository;
import com.example.simple_library_management_API.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.simple_library_management_API.controllers.BookController;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceDBImpl implements BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookServiceDBImpl.class);
    @Autowired
    private BookRepository bookRepository;
    @Override
    public Book addBook(Book book) {

        return bookRepository.save(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(int id) {
        Optional<Book> book =  bookRepository.findById(id);
        if(book.isPresent())
            return book.get();
        throw new ResourceNotFoundException("Book with id "+ id+ " Not Found");
    }

    @Override
    public Book borrowBook(int id) {
        Book book = getBookById(id);
        if (!book.isBorrowed()) {
            book.setBorrowed(true);
            bookRepository.save(book);
            logger.info("Borrowed: {}", book);
        } else {
            logger.info("Book already borrowed: {}", book);
        }
        return book;
    }

    @Override
    public Book returnBook(int id) {
        Book book = getBookById(id);
        if (book.isBorrowed()) {
            book.setBorrowed(false);
            bookRepository.save(book);
            logger.info("Returned: {}", book);
        } else {
            logger.info("Book was not borrowed: {}", book);
        }
        return book;
    }


    @Override
    public boolean deleteBook(int id) {
        bookRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Book with id "+ id + " Not Found"));
        bookRepository.deleteById(id);
        return true;
    }
}
