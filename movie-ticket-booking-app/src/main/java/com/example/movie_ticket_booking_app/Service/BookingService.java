package com.example.movie_ticket_booking_app.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.movie_ticket_booking_app.Model.Bookings;
import com.example.movie_ticket_booking_app.Model.Movies;
import com.example.movie_ticket_booking_app.Model.Users;
import com.example.movie_ticket_booking_app.Repository.BookingRepository;
import com.example.movie_ticket_booking_app.Repository.MovieRepository;
import com.example.movie_ticket_booking_app.Repository.UserRepository;

@Service
public class BookingService {
	private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;

    public BookingService(BookingRepository bookingRepository,
                          UserRepository userRepository,
                          MovieRepository movieRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
    }

    public List<Bookings> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Bookings getBookingById(Long id) {
        return bookingRepository.findById(id).orElse(null);
    }

    public Bookings createBooking(Long userId, Long movieId, String bookingName, String seatNumber) {
        Users user = userRepository.findById(userId).orElseThrow(() ->
                new RuntimeException("User not found"));
        Movies movie = movieRepository.findById(movieId).orElseThrow(() ->
                new RuntimeException("Movie not found"));

        Bookings booking = new Bookings();
        booking.setBookingName(bookingName);
        booking.setSeatNumber(seatNumber);
        booking.setUser(user);
        booking.setMovie(movie);

        return bookingRepository.save(booking);
    }

    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }

    public List<Bookings> getBookingsByUser(Long userId) {
        return bookingRepository.findByUserUserId(userId);
    }

    public List<Bookings> getBookingsByMovie(Long movieId) {
        return bookingRepository.findByMovieMovieId(movieId);
    }
}

