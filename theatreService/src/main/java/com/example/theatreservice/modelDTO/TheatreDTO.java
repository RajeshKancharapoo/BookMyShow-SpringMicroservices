package com.example.theatreservice.modelDTO;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TheatreDTO {

    @NotNull(message = "Theatre name cannot be null")
    @NotBlank(message = "Theatre name cannot be empty")
    private String theatreName;

    @NotNull(message = "Location cannot be null")
    @NotBlank(message = "Location cannot be empty")
    private String location;

    @Valid
    private List<ScreenDTO> screens;
}
