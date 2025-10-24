package com.example.movie_ticket_booking_app.DTO;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponseDTO {
	 private String message;
	 private String userName;
	 private String role;
}

