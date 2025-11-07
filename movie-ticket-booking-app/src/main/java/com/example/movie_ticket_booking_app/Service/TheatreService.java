package com.example.movie_ticket_booking_app.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.movie_ticket_booking_app.Model.Theatres;
import com.example.movie_ticket_booking_app.Repository.TheatreRepository;

@Service
public class TheatreService {

	private final TheatreRepository theatreRepository;
    public TheatreService(TheatreRepository theatreRepository) {
        this.theatreRepository = theatreRepository;
    }

    public List<Theatres> getAllTheatres() {
        return theatreRepository.findAll();
    }

    public Theatres getTheatreById(Long id) {
        return theatreRepository.findById(id).orElse(null);
    }

    public Theatres saveTheatre(Theatres theatre) {
        return theatreRepository.save(theatre);
    }
    
    public Theatres updateTheatre(Long id, Theatres theatreDetails) {
    	Theatres theatre = getTheatreById(id);
    	theatre.setTheatreName(theatreDetails.getTheatreName());
    	theatre.setLocation(theatreDetails.getLocation());  	
    	theatre.setScreenNumber(theatreDetails.getScreenNumber());
    	return theatreRepository.save(theatre);
    }

    public void deleteTheatre(Long id) {
        theatreRepository.deleteById(id);
    }
}

