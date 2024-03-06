package com.example.movieservice.modelDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LanguageDTO {

    @NotNull(message = "Language cannot be null")
    @NotBlank(message = "Language cannot be blank")
    private String language;
}
