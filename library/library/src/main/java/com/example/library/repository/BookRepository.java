package com.example.library.repository;

import com.example.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
public interface BookRepository extends JpaRepository<Book,Integer> {
    Page<Book> findByAuthorIgnoreCase(String author, Pageable pageable);
}