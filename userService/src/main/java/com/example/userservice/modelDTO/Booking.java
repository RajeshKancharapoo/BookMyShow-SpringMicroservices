package com.example.userservice.modelDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Booking {
    private Long bookingId;
    private Date bookingDate;
    private String bookingStatus;
    private String movieName;
    private String genre;
    private String language;
    private String runtime;
    private String theatreName;
    private String location;
    private String screenName;
}
