package com.example.cinestream.service;

import com.example.cinestream.entity.Director;
import com.example.cinestream.entity.Movie;
import com.example.cinestream.repository.DirectorRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Service;

@Service
public class DirectorService {
    private final DirectorRepository directorRepository;

    public DirectorService(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    public Director findDirectorById(Integer id) {
        return directorRepository.findById(id).orElseThrow(() -> new RuntimeException("Director with id: "+id+" not found"));
    }
}
