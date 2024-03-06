package com.example.userservice.feign;

import com.example.userservice.modelDTO.Movie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "movie-service",url = "http://localhost:8082/api/v1/movies")
public interface MovieFeign {

    @GetMapping("/all")
    ResponseEntity<List<Movie>> getMovies();
}
