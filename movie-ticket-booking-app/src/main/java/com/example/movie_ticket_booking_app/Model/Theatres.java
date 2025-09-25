package com.example.movie_ticket_booking_app.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "theatres")
@Data
public class Theatres {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private long theatreId;
	@Column(name = "theatre_name")
	private String theatreName;
	private String location;
	@Column(name = "screen_number")
	private String screenNumber;
	
	//Getters and Setters
	public Long getId() { return theatreId; }
	public void setId(Long id) { this.theatreId = id; }

	public String getTheatreName() { return theatreName; }
	public void setTheatreName(String theatreName) { this.theatreName = theatreName; }
	
	public String getLocation() { return location; }
	public void setLocation(String location) { this.location = location; }
	
	public String getScreenNumber() { return screenNumber; }
	public void setScreenNumber(String screenNumber) { this.screenNumber = screenNumber; }
}
