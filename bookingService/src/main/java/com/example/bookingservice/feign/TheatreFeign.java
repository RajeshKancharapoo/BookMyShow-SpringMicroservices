package com.example.bookingservice.feign;

import com.example.bookingservice.modelDTO.TheatreDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "theatre-service",url = "http://localhost:8083/api/v1/theatre")
public interface TheatreFeign {

    @GetMapping("/checkMovie/{movieName}/{theatreName}")
    ResponseEntity<Boolean> checkMovie(@PathVariable String movieName, @PathVariable String theatreName);


    @GetMapping("/theatre/{movieName}/{theatreName}")
    ResponseEntity<TheatreDetails>getTheatre(@PathVariable String movieName, @PathVariable String theatreName);

    @GetMapping("/update/{movieName}/{theatreName}")
    ResponseEntity<String>updateSeats(@PathVariable String movieName, @PathVariable String theatreName);
}
