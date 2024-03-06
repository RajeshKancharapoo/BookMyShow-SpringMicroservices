package com.example.theatreservice.repository;

import com.example.theatreservice.entity.Screen;
import com.example.theatreservice.entity.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TheatreRepo extends JpaRepository<Theatre, Long> {

    Optional<Theatre> findByTheatreName(String theatreName);
}
