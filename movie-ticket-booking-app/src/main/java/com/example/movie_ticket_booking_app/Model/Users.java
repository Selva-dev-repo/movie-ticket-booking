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
	
	@Column(nullable=false)
	private String role;
	
	@Column(name="mobile_number", length = 15, nullable =false)
    private String mobileNumber;
	
    @Column(nullable =false)
    private String address;
    
    @Column(length = 10, nullable =false)
    private String pincode;

    @Column(length = 100, nullable =false)
    private String city;

    @Column(length = 100, nullable =false)
    private String state;

    @Column(length = 100, nullable =false)
    private String country;
	
	//Getters and Setters
	public Long getUserId() { return userId; }
	public void setUserId(Long userId) { this.userId = userId; }

	public String getUserName() { return userName; }
	public void setUserName(String username) { this.userName = username; }

	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
	
	public String getRole() { return role; }
	public void setRole(String role) { this.role = role; } 
	
	public String getMobileNumber() { return mobileNumber; }
	public void setMobileNumber(String mobileNumber) { this.mobileNumber = mobileNumber; } 
	
	public String getAddress() { return address; }
	public void setAddress(String address) { this.address = address; } 
	
	public String getPincode() { return pincode; }
	public void setPincode(String pincode) { this.pincode = pincode; } 
	
	public String getCity() { return city; }
	public void setCity(String city) { this.city = city; } 
	
	public String getState() { return state; }
	public void setState(String state) { this.state = state; } 
	
	public String getCountry() { return country; }
	public void setCountry(String country) { this.country = country; } 
}
