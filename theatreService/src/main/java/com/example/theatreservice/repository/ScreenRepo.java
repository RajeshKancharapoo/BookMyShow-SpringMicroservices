package com.example.theatreservice.repository;

import com.example.theatreservice.entity.Screen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScreenRepo extends JpaRepository<Screen, Long> {

    Optional<Screen> findByMovieNameAndScreenName(String movieName, String screenName);
}
