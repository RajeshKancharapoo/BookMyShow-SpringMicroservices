package com.example.movieservice.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieID;
    private String movieName;
    private String genre;
    private String runtime;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "movieID", referencedColumnName = "movieID")
    private List<Language> languages;

}
