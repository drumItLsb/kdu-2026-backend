package com.example.library.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

// @Entity: Creates a table for this class
@Entity
public class Book {
    // @ID: Specifies Primary key
    // @GeneratedValue: Value generated automatically
    // GenerationType.AUTO :  Value is incremented sequentially
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    // these fields will be mapped to columns in the table
    // @NotBlank: performs validation at controller or service layer
    // , and it doesn't allow null, empty values, even string with just
    // spaces will be rejected
    // @Column(nullable = false) acts at database layer, provide extra security,
    // it rejects null values. Even if invalid data passes service/controller
    // layer, this acts as second line of defense

    @Column(nullable = false, unique = true)
    @NotBlank(message = "title is required")
    private String title;

    @NotBlank(message = "author is required")
    private String author;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Book() {}

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }
}
