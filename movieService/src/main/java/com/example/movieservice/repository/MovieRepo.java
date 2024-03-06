package com.example.movieservice.repository;

import com.example.movieservice.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieRepo extends JpaRepository<Movie,Long> {

    Optional<Movie> findByMovieName(String movieName);
}
