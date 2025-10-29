package com.example.movie_ticket_booking_app.Service;

import java.util.*;
import java.time.*;
import java.util.stream.Collectors;
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

    public Bookings createBooking(Long userId, Long movieId, Long theatreId, String bookingStatus, LocalDate showDate, String showTime, String seatNumber, BigDecimal amount) {
        Users user = userRepository.findById(userId).orElseThrow(() ->
                new RuntimeException("User not found"));
        Movies movie = movieRepository.findById(movieId).orElseThrow(() ->
                new RuntimeException("Movie not found"));
        Theatres theatre = theatreRepository.findById(theatreId).orElseThrow(() ->
        new RuntimeException("Theatre not found"));
        
        Bookings booking = new Bookings();
        Users users = new Users();
        booking.setBookingStatus(bookingStatus);
        booking.setSeatNumber(seatNumber);
        booking.setUser(user);
        booking.setMovie(movie);
        booking.setTheatre(theatre);
        booking.setShowDate(showDate);
        booking.setShowTime(showTime);
        booking.setAmount(amount);
        return bookingRepository.save(booking);
    }
    
    public Bookings saveBooking(Bookings booking) {
        return bookingRepository.save(booking);
    }
    
    public boolean[] getAvailabilityForSeats(
            List<String> seatLabels,
            Long theatreId,
            Long movieId,
            String showDateStr,
            String showTime) {

//        log.info("Checking seats: {}", seatLabels);

        LocalDate showDate = LocalDate.parse(showDateStr);

        List<Bookings> bookings = bookingRepository
                .findByTheatreTheatreIdAndMovieMovieIdAndShowDateAndShowTimeAndBookingStatus(
                        theatreId, movieId, showDate, showTime, "Confirmed");

        Set<String> bookedSeats = bookings.stream()
                .map(Bookings::getSeatNumber)
                .filter(Objects::nonNull)
                .filter(s -> !s.isBlank())
                .flatMap(s -> Arrays.stream(s.split("\\s*,\\s*")))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toSet());

//        log.info("Booked seats: {}", bookedSeats);

        boolean[] result = new boolean[seatLabels.size()];
        for (int i = 0; i < seatLabels.size(); i++) {
            result[i] = !bookedSeats.contains(seatLabels.get(i));
        }
        return result;
    }
    
    public Bookings cancelBooking(Long bookingId, Long userId) {
        Optional<Bookings> optionalBooking = bookingRepository.findById(bookingId);

        if (optionalBooking.isEmpty()) {
            throw new RuntimeException("Booking not found with ID: " + bookingId);
        }

        Bookings booking = optionalBooking.get();

        // Ensure the booking belongs to the logged-in user
        if (!booking.getUser().getUserId().equals(userId)) {
            throw new RuntimeException("Unauthorized to cancel this booking.");
        }

        booking.setBookingStatus("Cancelled");
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

