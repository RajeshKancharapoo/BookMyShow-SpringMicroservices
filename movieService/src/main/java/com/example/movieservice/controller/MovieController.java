package com.example.movieservice.controller;

import com.example.movieservice.entity.Movie;
import com.example.movieservice.exception.ErrMsgException;
import com.example.movieservice.modelDTO.MovieDTO;
import com.example.movieservice.modelDTO.MovieDetails;
import com.example.movieservice.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping("/add")
    public ResponseEntity<String>addMovie(@RequestBody @Valid MovieDTO movieDTO){
        return new ResponseEntity<>(movieService.addMovie(movieDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{movieName}")
    public ResponseEntity<Movie>getMovieByName(@PathVariable String movieName) throws ErrMsgException {
        return new ResponseEntity<>(movieService.getMovie(movieName),HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Movie>>getMovies(){
        return new ResponseEntity<>(movieService.allMovies(),HttpStatus.OK);
    }

    @GetMapping("/movie/{movieName}")
    public ResponseEntity<MovieDetails>getMovieDetails(@PathVariable String movieName) throws ErrMsgException {
        return new ResponseEntity<>(movieService.getMovieDetails(movieName),HttpStatus.OK);
    }
}
