package com.example.theatreservice.modelDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TheatreDetails {

    private String theatreName;
    private String location;
    private String screenName;
    private String language;

}
