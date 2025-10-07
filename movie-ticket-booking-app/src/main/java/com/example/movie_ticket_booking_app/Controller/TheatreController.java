package com.example.movie_ticket_booking_app.Controller;

import java.util.List;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import com.example.movie_ticket_booking_app.Model.Theatres;
import com.example.movie_ticket_booking_app.Repository.TheatreRepository;
import com.example.movie_ticket_booking_app.Service.TheatreService;

@RestController
@RequestMapping("/api/theatres")
@CrossOrigin(origins = "http://localhost:4200")
public class TheatreController {
	
	private final TheatreService theatreService;
    public TheatreController(TheatreService theatreService) {
        this.theatreService = theatreService;
    }

    @GetMapping
    public List<Theatres> getAllTheatres() {
        return theatreService.getAllTheatres();
    }

    @GetMapping("/{id}")
    public Theatres getTheatreById(@PathVariable Long id) {
        return theatreService.getTheatreById(id);
    }

    @PostMapping
    public Theatres createTheatre(@RequestBody Theatres theatre) {
        return theatreService.saveTheatre(theatre);
    }
    
    @PutMapping("/{id}")
    public Theatres updateTheatre(@PathVariable Long id, @RequestBody Theatres theatre) {
        return theatreService.updateTheatre(id, theatre);
    }

    @DeleteMapping("/{id}")
    public void deleteTheatre(@PathVariable Long id) {
        theatreService.deleteTheatre(id);
    }
}

