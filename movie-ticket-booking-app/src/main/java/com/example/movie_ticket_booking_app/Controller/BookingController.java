package com.example.movie_ticket_booking_app.Controller;

import java.math.BigDecimal;
import java.time.*;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;
import com.example.movie_ticket_booking_app.DTO.*;
import com.example.movie_ticket_booking_app.Model.Bookings;
import com.example.movie_ticket_booking_app.Service.BookingService;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "http://localhost:4200")
public class BookingController {
	private final BookingService bookingService;
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public List<Bookings> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/{id}")
    public Bookings getBooking(@PathVariable Long id) {
        return bookingService.getBookingById(id);
    }

    @PostMapping
    public Bookings createBooking(@RequestParam Long userId,
                                 @RequestParam Long movieId,
                                 @RequestParam Long theatreId,
                                 @RequestParam String bookingStatus,
                                 @RequestParam String showTime,
                                 @RequestParam LocalDate showDate,
                                 @RequestParam String seatNumber,
                                 @RequestParam BigDecimal amount) {
        return bookingService.createBooking(userId, movieId, theatreId, bookingStatus, showDate, showTime, seatNumber, amount);
    }
    
    @PostMapping("/check-availability")
    public ResponseEntity<boolean[]> checkAvailability(@RequestBody AvailabilityRequestDTO request) {
        boolean[] availability = bookingService.getAvailabilityForSeats(
                request.getSeats(),
                request.getTheatreId(),
                request.getMovieId(),
                request.getShowDate(),
                request.getShowTime()
        );
        return ResponseEntity.ok(availability);
    }
    
    @PutMapping("/{bookingId}/cancel")
    public ResponseEntity<Bookings> cancelBooking(@PathVariable Long bookingId,
    											  @RequestParam Long userId) {
        Bookings booking = bookingService.cancelBooking(bookingId, userId);
        return ResponseEntity.ok(booking);
    }

    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Bookings>> getBookingsByUser(@PathVariable Long userId) {
        List<Bookings> bookings = bookingService.getBookingsByUser(userId);
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/movie/{movieId}")
    public List<Bookings> getBookingsByMovie(@PathVariable Long movieId) {
        return bookingService.getBookingsByMovie(movieId);
    }
    
    @GetMapping("/theatre/{theatreId}")
    public List<Bookings> getBookingsByTheatre(@PathVariable Long theatreId) {
        return bookingService.getBookingsByTheatre(theatreId);
    }
}

