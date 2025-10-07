package com.example.movie_ticket_booking_app.Service;

import java.util.List;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;

import com.example.movie_ticket_booking_app.Model.Bookings;
import com.example.movie_ticket_booking_app.Model.Movies;
import com.example.movie_ticket_booking_app.Model.Theatres;
import com.example.movie_ticket_booking_app.Model.Users;
import com.example.movie_ticket_booking_app.Repository.BookingRepository;
import com.example.movie_ticket_booking_app.Repository.MovieRepository;
import com.example.movie_ticket_booking_app.Repository.UserRepository;
import com.example.movie_ticket_booking_app.Repository.TheatreRepository;

@Service
public class BookingService {
	private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final TheatreRepository theatreRepository;

    public BookingService(BookingRepository bookingRepository,
                          UserRepository userRepository,
                          MovieRepository movieRepository,
                          TheatreRepository theatreRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
        this.theatreRepository = theatreRepository;
    }

    public List<Bookings> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Bookings getBookingById(Long id) {
        return bookingRepository.findById(id).orElse(null);
    }

    public Bookings createBooking(Long userId, Long movieId, Long theatreId, String bookingStatus, String seatNumber, BigDecimal amount) {
        Users user = userRepository.findById(userId).orElseThrow(() ->
                new RuntimeException("User not found"));
        Movies movie = movieRepository.findById(movieId).orElseThrow(() ->
                new RuntimeException("Movie not found"));
        Theatres theatre = theatreRepository.findById(theatreId).orElseThrow(() ->
        new RuntimeException("Theatre not found"));
        
        Bookings booking = new Bookings();
        booking.setBookingStatus(bookingStatus);
        booking.setSeatNumber(seatNumber);
        booking.setUser(user);
        booking.setMovie(movie);
        booking.setTheatre(theatre);
        booking.setAmount(amount);
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
    
    public List<Bookings> getBookingsByTheatre(Long theatreId) {
        return bookingRepository.findByTheatreTheatreId(theatreId);
    }
}

