package com.example.cinestream.repository;

import com.example.cinestream.entity.Director;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectorRepository extends JpaRepository<Director,Integer> {
}
