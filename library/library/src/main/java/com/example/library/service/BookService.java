package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public Book addBook(Book book) {
        try {
            return bookRepository.save(book);
        } catch(DataIntegrityViolationException e) {
            // DataIntegrityViolationException: indicates database error like not unique, adding null
            throw new RuntimeException("Database error: "+ e.getMessage());
        }
    }

    public Page<Book> getAllBooks(String author, int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // Create Pageable object (page is 0-indexed in JPA)
        Pageable pageable = PageRequest.of(page, size, sort);
        if (author != null && !author.isEmpty()) {
            return bookRepository.findByAuthorIgnoreCase(author, pageable);
        }
        return bookRepository.findAll(pageable);
    }

    public Book updateBook(Book book, int id) {
        Book retrivedBook = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book with id: "+id+" not found"));
        retrivedBook.setTitle(book.getTitle());
        bookRepository.save(retrivedBook);
        return retrivedBook;
    }

    public Book findBookById(int id) {
        return bookRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Book with id: "+id+" not found"));
    }

    public void deleteBookById(int id) {
        if(!bookRepository.existsById(id)) {
            throw new RuntimeException("Book with id: "+id+" doesn't exist. So it can't be deleted.");
        }

        bookRepository.deleteById(id);
    }
}
