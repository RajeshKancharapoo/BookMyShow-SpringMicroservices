package com.example.movieservice.modelDTO;


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
public class MovieDTO {

    @NotNull(message = "Movie ID cannot be null")
    @NotBlank(message = "Movie ID cannot be blank")
    private String movieName;

    @NotNull(message = "Genre cannot be null")
    @NotBlank(message = "Genre cannot be blank")
    private String genre;

    @NotNull(message = "Runtime cannot be null")
    @NotBlank(message = "Runtime cannot be blank")
    private String runtime;

    @Valid
    private List<LanguageDTO>languageDTOS;
}
