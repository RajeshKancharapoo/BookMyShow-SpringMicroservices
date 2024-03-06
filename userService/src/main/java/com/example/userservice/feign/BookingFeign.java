package com.example.userservice.feign;

import com.example.userservice.modelDTO.Booking;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "booking-service", url = "http://localhost:8081/api/v1/booking")
public interface BookingFeign {

    @GetMapping("/book")
    ResponseEntity<Long> bookMovie(@RequestParam String movieName, @RequestParam String theatreName);

    @GetMapping("/cancel/{bookingId}")
    ResponseEntity<String>cancelBooking(@PathVariable Long bookingId);

    @PostMapping("/all")
    ResponseEntity<List<Booking>>allBookings(@RequestBody List<Long>bookingIds);
}
