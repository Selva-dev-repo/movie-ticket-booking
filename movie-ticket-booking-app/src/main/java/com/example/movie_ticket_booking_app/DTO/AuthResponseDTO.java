package com.example.movie_ticket_booking_app.DTO;
import lombok.*;
import com.example.movie_ticket_booking_app.Model.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponseDTO {
	private boolean success;
	 private String message;
	 private Users user;
}

