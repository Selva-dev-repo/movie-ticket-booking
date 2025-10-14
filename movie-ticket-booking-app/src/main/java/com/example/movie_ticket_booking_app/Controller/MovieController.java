package com.example.movie_ticket_booking_app.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.movie_ticket_booking_app.Model.Movies;
import com.example.movie_ticket_booking_app.Service.MovieService;

@RestController
@RequestMapping("/api/movies")
@CrossOrigin(origins = "http://localhost:4200")
public class MovieController {
	
	private final MovieService movieService;
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<Movies> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/{id}")
    public Movies getMovie(@PathVariable Long id) {
        return movieService.getMovieById(id);
    }
    
    @GetMapping("/upcoming")
    public List<Movies> getUpcomingMovies() {
        return movieService.getAllUpcoming();
    }

    @GetMapping("/released")
    public List<Movies> getReleasedMovies() {
        return movieService.getAllReleased();
    }
    
//    @PostMapping
//    public ResponseEntity<Movies> addMovie(@RequestBody Movies movie) {
//        return ResponseEntity.ok(movieService.addMovie(movie));
//    }

    @PostMapping
    public Movies createMovie(@RequestBody Movies movie) {
        return movieService.saveMovie(movie);
    }
    
    @PutMapping("/{id}")
    public Movies updateMovie(@PathVariable Long id, @RequestBody Movies movie) {
    	System.out.print(movie);
        return movieService.updateMovie(id, movie);
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
    }
}

