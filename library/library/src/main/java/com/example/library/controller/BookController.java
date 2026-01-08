package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<Book> addBook(@Valid @RequestBody Book book) {
        Book createdBook = bookService.addBook(book);
        if(createdBook != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
        } else return ResponseEntity.internalServerError().build();
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllBooks() {
        List<Book> returnedBooks = bookService.getAllBooks();
        Map<String,Object> result = new HashMap<String,Object>();

        if(returnedBooks.isEmpty()) {
            result.put("message","Inventory is Empty");
        }

        result.put("Content",returnedBooks);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Book> updateBook(@Valid @RequestBody Book book, @PathVariable int id) {
        Book updatedBook = bookService.updateBook(book,id);

        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable int id) {
        bookService.deleteBookById(id);
        return ResponseEntity.ok("Book with id: "+id+" deleted Successfully");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String,String> errors = new HashMap<String,String>();
        errors.put("timestamp", LocalDateTime.now().toLocalDate().toString());
        errors.put("error","Validation failed");
        // HttpStatus.BAD_REQUEST.value(): returns code for Bad Request : 400
        // Bad Request indicates client error
        errors.put("status", Integer.toString(HttpStatus.BAD_REQUEST.value()));

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName,errorMessage);
        });

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,String>> handleRuntimeExceptions(Exception ex){
        Map<String,String> errors = new HashMap<String,String>();
        errors.put("message",ex.getMessage());
        errors.put("timestamp", LocalDateTime.now().toLocalDate().toString());

        if(ex.getMessage().contains("Duplicate entry")) {
            // HttpStatus.CONFLICT.value(): returns code for Conflict : 409
            // Conflict indicates conflict with the current state of the target resource.
            errors.put("status", Integer.toString(HttpStatus.CONFLICT.value()));
            errors.put("message","Don't provide duplicate Entries");
            errors.put("error","Duplicate Entry");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errors);
        } else if(ex.getMessage().contains("deleted")) {
            errors.put("status",Integer.toString(HttpStatus.NO_CONTENT.value()));
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(errors);
        } else if(ex.getMessage().contains("not found")) {
            errors.put("status",Integer.toString(HttpStatus.NOT_FOUND.value()));
            errors.put("error","Book not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
        } else {
            // HttpStatus.INTERNAL_SERVER_ERROR.value(): returns code for Server Error : 500
            errors.put("status", Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()));
            errors.put("error","Internal Server Error");
        }
        return ResponseEntity.internalServerError().body(errors);
    }
}
