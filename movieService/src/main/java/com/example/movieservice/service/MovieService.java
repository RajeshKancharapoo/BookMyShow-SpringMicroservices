package com.example.movieservice.service;

import com.example.movieservice.entity.Language;
import com.example.movieservice.entity.Movie;
import com.example.movieservice.exception.ErrMsgException;
import com.example.movieservice.modelDTO.MovieDTO;
import com.example.movieservice.modelDTO.MovieDetails;
import com.example.movieservice.repository.MovieRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepo movieRepo;

    public Movie getMovie(String movieName) throws ErrMsgException {
        return movieRepo.findByMovieName(movieName).orElseThrow(()->new ErrMsgException("Movie not found"));
    }

    public List<Movie> allMovies() {
        return movieRepo.findAll();
    }


    public String addMovie(MovieDTO movieDTO) {
        List<Language>languages=movieDTO.getLanguageDTOS().stream().map(languageDTO -> Language.builder()
                .language(languageDTO.getLanguage())
                .build()).toList();

        Movie movie = Movie.builder()
                .movieName(movieDTO.getMovieName())
                .genre(movieDTO.getGenre())
                .runtime(movieDTO.getRuntime())
                .languages(languages)
                .build();

        movieRepo.save(movie);
        return "Movie added successfully";
    }

    public MovieDetails getMovieDetails(String movieName) {
        Movie movie = movieRepo.findByMovieName(movieName).orElse(null);
        if(movie==null){
            return null;
        }
        return MovieDetails.builder()
                .movieName(movie.getMovieName())
                .genre(movie.getGenre())
                .runtime(movie.getRuntime())
                .build();
    }
}
