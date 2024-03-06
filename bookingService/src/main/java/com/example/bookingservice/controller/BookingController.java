package com.example.bookingservice.controller;

import com.example.bookingservice.entity.Booking;
import com.example.bookingservice.exception.ErrMsgException;
import com.example.bookingservice.modelDTO.BookingResponse;
import com.example.bookingservice.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @GetMapping("/book")
    public ResponseEntity<Long>bookMovie(@RequestParam String movieName, @RequestParam String theatreName) throws ErrMsgException {
        return new ResponseEntity<>(bookingService.bookMovie(movieName,theatreName), HttpStatus.CREATED);
    }

    @GetMapping("/cancel/{bookingId}")
    public ResponseEntity<String>cancelBooking(@PathVariable Long bookingId) throws ErrMsgException {
        return new ResponseEntity<>(bookingService.cancelBooking(bookingId),HttpStatus.OK);
    }

    @PostMapping("/all")
    public ResponseEntity<List<Booking>>allBookings(@RequestBody List<Long>bookingIds){
        return new ResponseEntity<>(bookingService.allBookings(bookingIds),HttpStatus.OK);
    }




}
