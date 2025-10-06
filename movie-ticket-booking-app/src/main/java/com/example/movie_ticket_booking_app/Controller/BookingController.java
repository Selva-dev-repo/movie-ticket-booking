package com.example.movie_ticket_booking_app.Controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.movie_ticket_booking_app.Model.Bookings;
import com.example.movie_ticket_booking_app.Service.BookingService;

@RestController
@RequestMapping("/api/bookings")
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
                                 @RequestParam String bookingName,
                                 @RequestParam String seatNumber,
                                 @RequestParam BigDecimal amount) {
        return bookingService.createBooking(userId, movieId, bookingName, seatNumber, amount);
    }

    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
    }

    @GetMapping("/user/{userId}")
    public List<Bookings> getBookingsByUser(@PathVariable Long userId) {
        return bookingService.getBookingsByUser(userId);
    }

    @GetMapping("/movie/{movieId}")
    public List<Bookings> getBookingsByMovie(@PathVariable Long movieId) {
        return bookingService.getBookingsByMovie(movieId);
    }
}

