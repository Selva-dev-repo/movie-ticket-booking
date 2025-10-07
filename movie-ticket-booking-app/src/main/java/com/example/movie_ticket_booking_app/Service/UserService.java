package com.example.movie_ticket_booking_app.Service;
import java.util.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.movie_ticket_booking_app.Model.Users;
import com.example.movie_ticket_booking_app.Repository.UserRepository;

@Service
public class UserService {
	
	private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public String loginUser(String userName, String password) {
        // Find user by username
        Users storedUser = userRepository.findByUserName(userName);
        if (storedUser == null) {
            return "Invalid username or password";
        }

        // Verify password
        if (!storedUser.getPassword().equals(password)) {
            return "Invalid username or password";
        }

        // Successful login
        return "Login successful";
    }

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public Users getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public Users createUser(Users user) {
        return userRepository.save(user);
    }
    
    public Users updateUser(Long id, Users userDetails) {
        Users user = getUserById(id);
        user.setUserName(userDetails.getUserName());
        user.setPassword(userDetails.getPassword());
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

