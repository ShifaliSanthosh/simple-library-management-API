package com.example.simple_library_management_API.services;

import com.example.simple_library_management_API.models.Book;

import java.util.List;

public interface BookService {

    Book addBook(Book book);

    List<Book> getAllBooks();

    Book getBookById(int id);

    Book borrowBook(int id);

    Book returnBook(int id);

    boolean deleteBook(int id);

}
