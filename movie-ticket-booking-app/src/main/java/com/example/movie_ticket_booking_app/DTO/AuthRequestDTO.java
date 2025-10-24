package com.example.movie_ticket_booking_app.DTO;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequestDTO {
	private String userName;
	private String password;
}
