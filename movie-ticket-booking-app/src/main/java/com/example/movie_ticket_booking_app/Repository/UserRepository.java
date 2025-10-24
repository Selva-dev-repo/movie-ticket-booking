package com.example.movie_ticket_booking_app.Repository;

import com.example.movie_ticket_booking_app.Model.*;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
	 // Optional<Users> findByUserNameAndPassword(String userName, String Password);
	Users findByUserName(String userName);
}
