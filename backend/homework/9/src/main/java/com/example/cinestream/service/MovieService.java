package com.example.cinestream.service;

import com.example.cinestream.entity.Movie;
import com.example.cinestream.repository.MovieRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie findMovieById(@Argument Integer id) {
        return movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Movie with id: "+id+" not found"));
    }

    public Movie addReview(Integer id, String comment, Integer rating) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Movie with id: "+id+" not found"));

        movie.setComment(comment);
        movie.setRating(rating);

        movieRepository.save(movie);

        return movie;
    }

}
