package com.example.userservice.modelDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.codec.language.bm.Languages;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Movie {
    private Long movieID;
    private String movieName;
    private String genre;
    private String runtime;
    private List<Language> languages;
}
