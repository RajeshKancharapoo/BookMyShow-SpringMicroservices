package com.example.theatreservice.modelDTO;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.internal.build.AllowNonPortable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScreenDTO {

    @NotNull(message = "Screen name cannot be null")
    @NotBlank(message = "Screen name cannot be empty")
    private String screenName;

    @NotNull(message = "Movie name cannot be null")
    @NotBlank(message = "Movie name cannot be empty")
    private String movieName;

    @NotNull(message = "Number of seats cannot be null")
    @Min(value = 100, message = "Number of seats should be greater than 99")
    private Integer noOfSeats;

    @NotNull(message = "Language cannot be null")
    @NotBlank(message = "Language cannot be empty")
    private String language;
}
