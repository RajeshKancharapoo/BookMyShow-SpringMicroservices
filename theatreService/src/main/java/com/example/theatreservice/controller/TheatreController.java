package com.example.theatreservice.controller;

import com.example.theatreservice.entity.Theatre;
import com.example.theatreservice.exception.ErrMsgException;
import com.example.theatreservice.modelDTO.TheatreDTO;
import com.example.theatreservice.modelDTO.TheatreDetails;
import com.example.theatreservice.service.TheatreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/theatre")
@RequiredArgsConstructor
public class TheatreController {

    private final TheatreService theatreService;

    @GetMapping("/all")
    public ResponseEntity<List<Theatre>>allTheatres(){
        return new ResponseEntity<>(theatreService.getAllTheatres(), HttpStatus.OK);
    }

    @GetMapping("/getTheatreByName/{theatreName}")
    public ResponseEntity<Theatre> getTheatreByName(@PathVariable String theatreName) throws ErrMsgException {
        return new ResponseEntity<>(theatreService.getTheatreByName(theatreName), HttpStatus.OK);
    }

    @PostMapping("/addTheatre")
    public ResponseEntity<String>addTheatre(@RequestBody @Valid TheatreDTO theatreDTO){
        return new ResponseEntity<>(theatreService.addTheatre(theatreDTO), HttpStatus.CREATED);
    }

   @GetMapping("/checkMovie/{movieName}/{theatreName}")
    public ResponseEntity<Boolean>checkMovie(@PathVariable String movieName,@PathVariable String theatreName){
        return new ResponseEntity<>(theatreService.checkMovie(movieName,theatreName),HttpStatus.OK);
    }

    @GetMapping("/theatre/{movieName}/{theatreName}")
    public ResponseEntity<TheatreDetails>getTheatre(@PathVariable String movieName, @PathVariable String theatreName) throws ErrMsgException {
        return new ResponseEntity<>(theatreService.getTheatre(movieName,theatreName),HttpStatus.OK);
    }

    @GetMapping("update/{movieName}/{theatreName}")
    public ResponseEntity<String>updateSeats(@PathVariable String movieName, @PathVariable String theatreName) throws ErrMsgException {
        return new ResponseEntity<>(theatreService.updateSeats(movieName,theatreName),HttpStatus.OK);
    }
}
