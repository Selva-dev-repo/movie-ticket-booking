package com.example.movie_ticket_booking_app.Repository;

import com.example.movie_ticket_booking_app.Model.*;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movies, Long> {
	List<Movies> findByMovieStatus(String movieStatus);
}

