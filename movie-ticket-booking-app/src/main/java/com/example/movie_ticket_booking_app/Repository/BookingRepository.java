package com.example.movie_ticket_booking_app.Repository;

import com.example.movie_ticket_booking_app.Model.*;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Bookings, Long> {
	List<Bookings> findByUserUserId(Long userId);
    List<Bookings> findByMovieMovieId(Long movieId);
}
