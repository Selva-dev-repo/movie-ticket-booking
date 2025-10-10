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
    	Optional<Users> storedUserOpt = userRepository.findByUserName(userName);
        if (storedUserOpt.isEmpty()) {
            return "Invalid username or password";
        }
        Users storedUser = storedUserOpt.get();
        if (!storedUser.getPassword().equals(password)) {
            return "Invalid username or password";
        }
        return "Login successful";
    }
    
    public Users getUserByUserName(String userName) {
        return userRepository.findByUserName(userName).orElse(null);
    }

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public Users getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public Users createUser(Users user) {
    	if ("admin".equals(user.getRole()) && userRepository.findByUserName("admin").isPresent()) {
            throw new IllegalArgumentException("Admin user already exists");
        }
        return userRepository.save(user);
    }
    
    public Users updateUser(Long id, Users userDetails) {
        Users user = getUserById(id);
        
        if ("admin".equals(userDetails.getRole()) && !user.getUserName().equals("admin") && userRepository.findByUserName("admin").isPresent()) {
            throw new IllegalArgumentException("Cannot assign admin role; admin already exists");
        }
        user.setUserName(userDetails.getUserName());
        user.setPassword(userDetails.getPassword());
        user.setRole(userDetails.getRole());
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
    	Users user = getUserById(id);
        if (user != null && "admin".equals(user.getRole())) {
            throw new IllegalArgumentException("Cannot delete admin user");
        }
        userRepository.deleteById(id);
    }
}

