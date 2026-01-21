package com.example.cinestream.controller;

import com.example.cinestream.entity.Director;
import com.example.cinestream.entity.Movie;
import com.example.cinestream.service.DirectorService;
import com.example.cinestream.service.MovieService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }


    @QueryMapping
    Movie findMovieById(@Argument Integer id) {
        return movieService.findMovieById(id);
    }

    @MutationMapping
    Movie addReview(@Argument Integer id, @Argument String comment,@Argument Integer rating) {
        return movieService.addReview(id,comment,rating);
    }

}
