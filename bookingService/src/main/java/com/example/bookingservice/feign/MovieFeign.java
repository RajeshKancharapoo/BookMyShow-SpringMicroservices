package com.example.bookingservice.feign;


import com.example.bookingservice.exception.ErrMsgException;
import com.example.bookingservice.modelDTO.MovieDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "movie-service",url = "http://localhost:8082/api/v1/movies")
public interface MovieFeign {


    @GetMapping("/movie/{movieName}")
    ResponseEntity<MovieDetails> getMovieDetails(@PathVariable String movieName);
}
