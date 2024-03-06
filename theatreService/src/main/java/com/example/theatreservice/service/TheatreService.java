package com.example.theatreservice.service;


import com.example.theatreservice.entity.Screen;
import com.example.theatreservice.entity.Theatre;
import com.example.theatreservice.exception.ErrMsgException;
import com.example.theatreservice.modelDTO.ScreenDTO;
import com.example.theatreservice.modelDTO.TheatreDTO;
import com.example.theatreservice.modelDTO.TheatreDetails;
import com.example.theatreservice.repository.ScreenRepo;
import com.example.theatreservice.repository.TheatreRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TheatreService {

    private final TheatreRepo theatreRepo;
    private final ScreenRepo screenRepo;

    public List<Theatre> getAllTheatres() {
        return theatreRepo.findAll();
    }

    public Theatre getTheatreByName(String theatreName) throws ErrMsgException {
        return theatreRepo.findByTheatreName(theatreName).orElseThrow(() -> new ErrMsgException("Theatre not found"));
    }

    public String addTheatre(TheatreDTO theatreDTO) {
        List<Screen>screens= theatreDTO.getScreens().stream().map(screenDTO -> Screen.builder()
                .screenName(screenDTO.getScreenName())
                .noOfSeats(screenDTO.getNoOfSeats())
                .movieName(screenDTO.getMovieName())
                .language(screenDTO.getLanguage())
                .build()).toList();

        Theatre theatre = Theatre.builder()
                .theatreName(theatreDTO.getTheatreName())
                .location(theatreDTO.getLocation())
                .screens(screens)
                .build();

        theatreRepo.save(theatre);
        return "Theatre added successfully";
    }

    public Boolean checkMovie(String movieName, String theatreName) {
        Theatre theatre = theatreRepo.findByTheatreName(theatreName).orElse(null);
        if(theatre==null){
            return false;
        }
        List<Screen>screens=theatre.getScreens();
        for(Screen screen:screens){
            if(screen.getMovieName().equals(movieName)){
                return true;
            }
        }
        return false;
    }

    public TheatreDetails getTheatre(String movieName, String theatreName) throws ErrMsgException {
        var theatre = theatreRepo.findByTheatreName(theatreName).orElse(null);
        if(theatre==null){
            return null;
        }
        List<Screen> screens=theatre.getScreens();
        for(int i=0;i<screens.size();++i){
            Screen screen=screens.get(i);
            if(screen.getMovieName().equals(movieName)){
                screen.setNoOfSeats(screen.getNoOfSeats()-1);
                screens.set(i,screen);
            }
        }
        theatre.setScreens(screens);
        theatreRepo.save(theatre);


        for(Screen screen:screens){
            if(screen.getMovieName().equals(movieName) && screen.getNoOfSeats()>0){
                return TheatreDetails.builder()
                        .theatreName(theatre.getTheatreName())
                        .location(theatre.getLocation())
                        .screenName(screen.getScreenName())
                        .language(screen.getLanguage())
                        .build();
            }
        }
        throw new ErrMsgException("No seats available");
    }

    public String updateSeats(String movieName,String screenName) throws ErrMsgException {
        Screen screen=screenRepo.findByMovieNameAndScreenName(movieName,screenName).orElse(null);
        if(screen==null){
            throw new ErrMsgException("Screen not found");
        }
        screen.setNoOfSeats(screen.getNoOfSeats()+1);
        screenRepo.save(screen);
        return "Seats updated successfully";
    }
}
