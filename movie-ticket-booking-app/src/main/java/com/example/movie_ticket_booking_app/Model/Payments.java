package com.example.movie_ticket_booking_app.Model;
import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "payments")
@Data
public class Payments {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long paymentId;
	 private String paymentStatus;
	 private BigDecimal amount;

	    @OneToOne
	    @JoinColumn(name = "booking_id", nullable = false)
	    private Bookings booking;

		public void setPaymentStatus(String paymentStatus2) {
			// TODO Auto-generated method stub
			
		}

		public void setBooking(Bookings booking2) {
			// TODO Auto-generated method stub
			
		}

}

