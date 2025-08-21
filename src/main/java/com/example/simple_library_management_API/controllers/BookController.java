package com.example.simple_library_management_API.controllers;

import com.example.simple_library_management_API.exceptions.ResourceNotFoundException;
import com.example.simple_library_management_API.models.Book;
import com.example.simple_library_management_API.services.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);
    @Autowired
    public BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        logger.info("Inside getAllBooks");
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/{id}")
    public Book getBookId(@PathVariable int id) {
        try {
            return bookService.getBookById(id);
        } catch (ResourceNotFoundException e) {
            logger.warn(e.getMessage());
            throw e;
        }
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book)
    {
        Book newbook = bookService.addBook(book);
        if(newbook == null)
        {
            return ResponseEntity.noContent().build();
        } return new ResponseEntity<Book>(newbook, HttpStatus.CREATED);

    }


    @PutMapping("/{id}/borrow")
    public ResponseEntity<String> borrowBook(@PathVariable int id) {
        Book borrowed = bookService.borrowBook(id);
        if (borrowed.isBorrowed()) {
            return ResponseEntity.ok("Borrowed Successfully: " + borrowed.getTitle());
        } else {
            return ResponseEntity.ok("Book " + borrowed.getTitle() + " not yet borrowed");
        }
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<String> returnBook(@PathVariable int id) {
        Book returned = bookService.returnBook(id);
        if (!returned.isBorrowed()) {
            return ResponseEntity.ok("Returned Successfully: " + returned.getTitle());
        } else {
            return ResponseEntity.ok("Book " + returned.getTitle() + " not yet returned");
        }
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable int id) {
        boolean deleted = bookService.deleteBook(id);
        if (!deleted) {
            throw new ResourceNotFoundException("NOT FOUND");
        } else {
            return "Successfully Deleted";
        }
    }
}

