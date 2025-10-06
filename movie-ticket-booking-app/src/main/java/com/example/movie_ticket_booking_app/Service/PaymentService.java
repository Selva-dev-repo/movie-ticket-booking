package com.example.movie_ticket_booking_app.Service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.movie_ticket_booking_app.Model.Payments;
import com.example.movie_ticket_booking_app.Model.Bookings;
import com.example.movie_ticket_booking_app.Repository.BookingRepository;
import com.example.movie_ticket_booking_app.Repository.PaymentRepository;

@Service
public class PaymentService {

	private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;

    public PaymentService(PaymentRepository paymentRepository, BookingRepository bookingRepository) {
        this.paymentRepository = paymentRepository;
        this.bookingRepository = bookingRepository;
    }

    public List<Payments> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payments getPaymentById(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

    public Payments createPayment(Long bookingId, String paymentStatus) {
        Bookings booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        Payments payment = new Payments();
        payment.setPaymentStatus(paymentStatus);
        payment.setBooking(booking);
        return paymentRepository.save(payment);
    }

    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }
}

