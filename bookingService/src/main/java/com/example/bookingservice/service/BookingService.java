package com.example.bookingservice.service;


import com.example.bookingservice.entity.Booking;
import com.example.bookingservice.exception.ErrMsgException;
import com.example.bookingservice.feign.MovieFeign;
import com.example.bookingservice.feign.TheatreFeign;
import com.example.bookingservice.modelDTO.BookingResponse;
import com.example.bookingservice.modelDTO.MovieDetails;
import com.example.bookingservice.modelDTO.TheatreDetails;
import com.example.bookingservice.repository.BookingRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingService {

    private final BookingRepo bookingRepo;
    private final MovieFeign movieFeign;
    private final TheatreFeign theatreFeign;
    public String cancelBooking(Long bookingId) throws ErrMsgException {
        //1.update the booking status to cancelled in the database
        //2.call the theatre service to update the available seats
        var booking = bookingRepo.findById(bookingId).orElseThrow(() -> new ErrMsgException("Booking not found"));
        booking.setBookingStatus("CANCELLED");
        bookingRepo.save(booking);
        theatreFeign.updateSeats(booking.getMovieName(),booking.getScreenName());
        return "Booking cancelled successfully";
    }

    public Long bookMovie(String movieName, String theatreName) throws ErrMsgException {
        //1. check if movie is playing in the theatre
        //2.if ans is false return movie not playing in the theatre
        //3.else call the movie service to get the movie details anc call the theatre service to get the theatre details
        //4.save the booking details in the database combining the movie and theatre details
        //5.also update the theatre details to reduce the available seats
        ResponseEntity<Boolean>responseEntity=theatreFeign.checkMovie(movieName, theatreName);
        log.info("responseEntity:{}",responseEntity.getBody());
        if(!responseEntity.getBody()){
            return null;
        }
        ResponseEntity<MovieDetails>movieDetailsResponseEntity=movieFeign.getMovieDetails(movieName);
        ResponseEntity<TheatreDetails>theatreDetailsResponseEntity=theatreFeign.getTheatre(movieName, theatreName);

        var movieDetails=movieDetailsResponseEntity.getBody();
        var theatreDetails=theatreDetailsResponseEntity.getBody();

        assert movieDetails != null;
        assert theatreDetails != null;
        var booking = Booking.builder()
               .bookingDate(new Date())
               .bookingStatus("BOOKED")
               .movieName(movieDetails.getMovieName())
               .genre(movieDetails.getGenre())
               .language(theatreDetails.getLanguage())
               .runtime(movieDetails.getRuntime())
               .theatreName(theatreDetails.getTheatreName())
               .location(theatreDetails.getLocation())
               .screenName(theatreDetails.getScreenName())
               .build();

        bookingRepo.save(booking);
        return booking.getBookingId();
    }

    public List<Booking> allBookings(List<Long>bookingIds) {
        return bookingIds.stream().map(id->bookingRepo.findById(id).get()).toList();
    }
}
