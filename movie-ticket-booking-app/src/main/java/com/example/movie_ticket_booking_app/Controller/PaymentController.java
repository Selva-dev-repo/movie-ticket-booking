package com.example.movie_ticket_booking_app.Controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.movie_ticket_booking_app.Model.Payments;
import com.example.movie_ticket_booking_app.Service.PaymentService;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
	
	private final PaymentService paymentService;
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public List<Payments> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/{id}")
    public Payments getPayment(@PathVariable Long id) {
        return paymentService.getPaymentById(id);
    }

    @PostMapping
    public Payments createPayment(@RequestParam Long bookingId,
                                 @RequestParam String paymentStatus) {
        return paymentService.createPayment(bookingId, paymentStatus);
    }

    @DeleteMapping("/{id}")
    public void deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
    }
}

