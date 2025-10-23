package com.example.movie_ticket_booking_app.Config;

import java.util.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

	@Bean
    public PasswordEncoder passwordEncoder() {
        System.out.println("Creating BCryptPasswordEncoder bean"); // Debug log to confirm bean creation
        return new BCryptPasswordEncoder();
    }

//	@Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//            // Disable CSRF since you're likely using Angular frontend (REST API)
//            .csrf(csrf -> csrf.disable())
//            .cors(withDefaults()) 
//            // Define endpoint access rules
//            .authorizeHttpRequests(auth -> auth
//                .requestMatchers("/api/login").permitAll() // ✅ allow login & register
//                .anyRequest().authenticated() // everything else requires auth
//            )
//
//            // Optional: Use HTTP Basic for testing (you can remove if using JWT)
//            .httpBasic(withDefaults());
//
//        return http.build();
//    } 
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // Disable CSRF for Postman testing
            .authorizeHttpRequests()
            .anyRequest().permitAll(); // Allow all requests
        return http.build();
    }
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
	    CorsConfiguration configuration = new CorsConfiguration();
	    configuration.setAllowedOrigins(List.of("http://localhost:4200")); // your Angular app origin
	    configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
	    configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
	    configuration.setAllowCredentials(true);

	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", configuration);
	    return source;
	}

}
