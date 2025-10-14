package com.example.movie_ticket_booking_app.Model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "movies")
@Data
public class Movies {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private long movieId;
	@Column(name = "movie_name") 
	private String movieTitle;
	@Column(name = "duration")
	private int duration;
	@Column(name = "genre")
	private String genre;
	private String poster;
	@Column(name = "release_date")
	private LocalDate releaseDate;
	@Column(name = "movie_status")
	private String movieStatus;
	
	//Getters and Setters
	public Long getMovieId() { return movieId; }
	public void setMovieId(Long movieId) { this.movieId = movieId; }

	public String getMovieTitle() { return movieTitle; }
	public void setMovieTitle(String movieTitle) { this.movieTitle = movieTitle; }
	
	public int getDuration() { return duration; }
	public void setDeuration(int duration) { this.duration = duration; }
 
	public String getGenre() { return genre; }
	public void setGenre(String genre) { this.genre = genre; }
	
	public String getPoster() { return poster; }
	public void setPoster(String poster) { this.poster = poster; }
	
	public LocalDate getReleaseDate() { return releaseDate; }
	public void setReleaseDate(LocalDate releaseDate) { this.releaseDate = releaseDate; }
	
	public String getMovieStatus() { return movieStatus; }
	public void setMovieStatus(String movieStatus) { this.movieStatus = movieStatus; }
}

