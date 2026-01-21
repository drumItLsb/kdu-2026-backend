package com.example.cinestream.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "title is required")
    private String title;


    @NotBlank(message = "genre is required")
    private String genre;

    @ManyToOne(optional = false)
    @JoinColumn(
            name = "director_id",           // FK column in movie table
            referencedColumnName = "id"
    )
    private Integer directorId;
}
