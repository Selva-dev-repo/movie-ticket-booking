package com.example.movie_ticket_booking_app.DTO;

import lombok.Data;
import java.util.List;

@Data
public class AvailabilityRequestDTO {
	private List<String> seats;      
    private Long theatreId;
    private Long movieId;
    private String showDate;        
    private String showTime;
}
