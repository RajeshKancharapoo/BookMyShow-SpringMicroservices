package com.example.bookingservice.modelDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingResponse {

    private String movieName;
    private String genre;
    private String runtime;
    private String language;
    private String theatreName;
    private String location;
    private String screenName;
}
