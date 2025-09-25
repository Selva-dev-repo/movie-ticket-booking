package com.example.movie_ticket_booking_app.Model;

import jakarta.persistence.*;
//import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long userId;
//	@NotBlank(message = "Username is required")
	@Column(name = "user_name", nullable = false) 
	private String userName;
//	@NotBlank(message = "password is required")
	@Column(nullable = false)
	private String password;
	
	//Getters and Setters
	public Long getId() { return userId; }
	public void setId(Long id) { this.userId = id; }

	public String getUserName() { return userName; }
	public void setUserName(String username) { this.userName = username; }

	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
}
