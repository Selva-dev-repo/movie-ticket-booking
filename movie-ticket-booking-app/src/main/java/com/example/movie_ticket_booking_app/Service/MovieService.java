package com.example.movie_ticket_booking_app.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.movie_ticket_booking_app.Model.Movies;
import com.example.movie_ticket_booking_app.Repository.MovieRepository;

@Service
public class MovieService {
	
	private final MovieRepository movieRepository;
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movies> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movies getMovieById(Long id) {
        return movieRepository.findById(id).orElse(null);
    }
    
    public List<Movies> getAllUpcoming() {
    	return movieRepository.findByMovieStatus("Coming Soon");
    }

    public List<Movies> getAllReleased() {
        return movieRepository.findByMovieStatus("Released");
    }

    public Movies saveMovie(Movies movie) {
        return movieRepository.save(movie);
    }
    
    public Movies updateMovie(Long id, Movies movieDetails) {
		Movies movie = getMovieById(id);
		movie.setMovieTitle(movieDetails.getMovieTitle());
		movie.setDuration(movieDetails.getDuration());
		movie.setGenre(movieDetails.getGenre());
		movie.setPoster(movieDetails.getPoster());
		movie.setReleaseDate(movieDetails.getReleaseDate());
		movie.setMovieStatus(movieDetails.getMovieStatus());
		return movieRepository.save(movie);
	}

    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }
}

