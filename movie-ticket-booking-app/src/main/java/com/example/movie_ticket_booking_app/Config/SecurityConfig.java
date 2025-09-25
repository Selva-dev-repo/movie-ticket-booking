package com.example.movie_ticket_booking_app.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

@Component
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	http
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/login", "/css/**", "/js/**").permitAll() // allow login page and static resources
            .anyRequest().authenticated() // everything else requires auth
        )
        .formLogin(form -> form
            .loginPage("/login") // your login page
            .defaultSuccessUrl("/adduser", true) // optional, where to go after login
            .permitAll()
        )
        .logout(logout -> logout.permitAll());
    	return null;
//    	return http.build();
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()
            );
        return http.build();
    }
}

