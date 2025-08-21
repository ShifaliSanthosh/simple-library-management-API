# Book Management API

This is a Spring Boot project that provides a RESTful API for managing books, specifically for borrowing and returning books.

## Features

- Adds book
- Gets all books
- Gets Book by ID
- Borrow a book by ID
- Return a book by ID
- Logs actions and book status
- Handles book not found scenarios

## Technologies Used

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- PostgreSQL
- SLF4J for logging

##Set Up Instructions

1. Clone the repository

   git clone https://github.com/ShifaliSanthosh/simple-library-management-api.git
   
3. Built the project
   
   mvn clean install
   
4. Run the application

   mvn spring-boot:run

5. Access API point at:

   http://localhost:8000/book

   
