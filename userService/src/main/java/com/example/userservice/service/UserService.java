package com.example.userservice.service;

import com.example.userservice.entity.BookingIDS;
import com.example.userservice.entity.User;
import com.example.userservice.exception.ErrMsgException;
import com.example.userservice.feign.BookingFeign;
import com.example.userservice.feign.MovieFeign;
import com.example.userservice.feign.TheatreFeign;
import com.example.userservice.modelDTO.Booking;
import com.example.userservice.modelDTO.Movie;
import com.example.userservice.modelDTO.UserDTO;
import com.example.userservice.repository.BookingRepo;
import com.example.userservice.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final MovieFeign movieFeign;
    private final TheatreFeign theatreFeign;
    private final BookingFeign bookingfeign;
    private final BookingRepo bookingRepo;
    public String addUser(String username,UserDTO userDTO){
       userRepo.save(User.builder().username(username).phNo(userDTO.getPhNo()).build());
        return "user added successfully";
    }
    public String bookMovie(String username,String movieName,String theatreName ){
        //1. delegate this request to the booking service
        Long bookingId = bookingfeign.bookMovie(movieName,theatreName).getBody();
        if(bookingId!=null){
            bookingRepo.save(BookingIDS.builder().bookingID(bookingId).username(username).build());
            return "movie booked successfully";
        }
        return "movie is not playing in the given theatre";
    }

    public String cancelMovie(String username,Long bookingId) throws ErrMsgException {
        BookingIDS id=bookingRepo.findById(bookingId).orElseThrow(()->new ErrMsgException("BookingID is not valid"));
        if(!id.getUsername().equals(username)){
            throw new ErrMsgException("You are not authorized to cancel this booking");
        }
        bookingfeign.cancelBooking(bookingId);
        return "Booking cancelled successfully";
    }

    public List<Movie> viewMovies(){
        //1. delegate this request to the movie service and it does not require any authentication
        return movieFeign.getMovies().getBody();
    }

    public List<Booking> viewBookings(String username) {

        List<BookingIDS>bookingIDS=bookingRepo.findAll();
        List<Long>ids=bookingIDS.stream().filter(id->id.getUsername().equals(username)).map(BookingIDS::getBookingID).toList();
        return bookingfeign.allBookings(ids).getBody();
    }
}
