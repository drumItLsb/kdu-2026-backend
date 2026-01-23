package com.example.cinestream.controller;

import com.example.cinestream.entity.Director;
import com.example.cinestream.entity.Movie;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
public class MovieResolver {
    @SchemaMapping(typeName = "Movie", field = "director")
    public Director director(Movie movie) {
        return movie.getDirectorId();
    }
}
