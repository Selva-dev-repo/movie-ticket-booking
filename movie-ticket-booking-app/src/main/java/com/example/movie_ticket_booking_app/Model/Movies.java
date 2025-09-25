package com.example.movie_ticket_booking_app.Model;

import jakarta.persistence.*;
import lombok.*;

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
	@Column(name = "show_time")
	private String showTime;
	
	//Getters and Setters
	public Long getId() { return movieId; }
	public void setId(Long id) { this.movieId = id; }

	public String getMovieTitle() { return movieTitle; }
	public void setMovieTitle(String movieTitle) { this.movieTitle = movieTitle; }
	
	public int getDuration() { return duration; }
	public void setDeuration(int duration) { this.duration = duration; }
 
	public String getShowTime() { return showTime; }
	public void setShowTime(String showTime) { this.showTime = showTime; }
}

