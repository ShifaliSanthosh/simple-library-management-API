package com.example.simple_library_management_API.repositories;

import com.example.simple_library_management_API.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer>{
}

