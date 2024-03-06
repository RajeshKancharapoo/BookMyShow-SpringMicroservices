package com.example.userservice.controller;


import com.example.userservice.exception.ErrMsgException;
import com.example.userservice.modelDTO.Booking;
import com.example.userservice.modelDTO.Movie;
import com.example.userservice.modelDTO.UserDTO;
import com.example.userservice.repository.UserRepo;
import com.example.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepo userRepo;
    private final UserService userService;

    @GetMapping("/test")
    public ResponseEntity<String> test(@RequestHeader("currUser") String username){
        return new ResponseEntity<>("hello "+username, HttpStatus.OK);
    }

    @GetMapping("/allMovies")
    public ResponseEntity<List<Movie>>viewMovies(){
        return new ResponseEntity<>(userService.viewMovies(),HttpStatus.OK);
    }

    @GetMapping("/book")
    public ResponseEntity<String> bookMovie(@RequestHeader("currUser") String username,@RequestParam String movieName,@RequestParam String theatreName) throws ErrMsgException {
        userRepo.findByUsername(username).orElseThrow(()->new ErrMsgException("Please signup to perform this action"));
        return new ResponseEntity<>(userService.bookMovie(username,movieName,theatreName),HttpStatus.OK);
    }

    @GetMapping("/cancel")
    public ResponseEntity<String>cancelMovie(@RequestHeader("currUser") String username,@RequestParam  Long bookingId) throws ErrMsgException {
        userRepo.findByUsername(username).orElseThrow(()->new ErrMsgException("Please signup to perform this action"));
        return new ResponseEntity<>(userService.cancelMovie(username,bookingId),HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestHeader("currUser") String username,@RequestBody @Valid UserDTO userDTO){
        return new ResponseEntity<>(userService.addUser(username,userDTO), HttpStatus.OK);
    }

    @GetMapping("/viewBookings")
    public ResponseEntity<List<Booking>>viewBookings(@RequestHeader("currUser") String username) throws ErrMsgException {
        userRepo.findByUsername(username).orElseThrow(()->new ErrMsgException("Please signup to perform this action"));
        return new ResponseEntity<>(userService.viewBookings(username),HttpStatus.OK);
    }

}
