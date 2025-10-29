package com.example.movie_ticket_booking_app.Repository;

import com.example.movie_ticket_booking_app.Model.*;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Bookings, Long> {
	List<Bookings> findByUserUserId(Long userId);
    List<Bookings> findByMovieMovieId(Long movieId);
    List<Bookings> findByTheatreTheatreId(Long theatreId);
    List<Bookings> findByTheatreTheatreIdAndMovieMovieIdAndShowDateAndShowTimeAndBookingStatus(
            Long theatreId,
            Long movieId,
            LocalDate showDate,
            String showTime,
            String bookingStatus
    );
}
