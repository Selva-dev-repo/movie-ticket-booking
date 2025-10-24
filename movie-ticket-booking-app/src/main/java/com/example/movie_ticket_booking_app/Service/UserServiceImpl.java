package com.example.movie_ticket_booking_app.Service;

import com.example.movie_ticket_booking_app.Model.Users;
import com.example.movie_ticket_booking_app.DTO.AuthRequestDTO;
import com.example.movie_ticket_booking_app.DTO.AuthResponseDTO;
import com.example.movie_ticket_booking_app.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AuthResponseDTO loginUser(AuthRequestDTO loginRequest) throws Exception {
        Users user = userRepository.findByUserName(loginRequest.getUserName());
        if (user != null && passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            user.setPassword(null); 
            return new AuthResponseDTO(true, "Login successful", user);
        } else {
            return new AuthResponseDTO(false, "Invalid username or password", null);
        }
    }

    @Override
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Users getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public Users createUser(Users user) throws Exception {
        if (userRepository.findByUserName(user.getUserName()) != null) {
            throw new Exception("Username already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Users updateUser(Long id, Users user) throws Exception {
        Optional<Users> existingUser = userRepository.findById(id);
        if (existingUser.isEmpty()) {
            return null;
        }
        Users userToUpdate = existingUser.get();
        userToUpdate.setUserName(user.getUserName());
        
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            userToUpdate.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        if (user.getRole() != null) {
            userToUpdate.setRole(user.getRole());
        }
        if (user.getMobileNumber() != null) {
            userToUpdate.setMobileNumber(user.getMobileNumber());
        }
        if (user.getAddress() != null) {
            userToUpdate.setAddress(user.getAddress());
        }
        if (user.getPincode() != null) {
            userToUpdate.setPincode(user.getPincode());
        }
        if (user.getCity() != null) {
            userToUpdate.setCity(user.getCity());
        }
        if (user.getState() != null) {
            userToUpdate.setState(user.getState());
        }
        if (user.getCountry() != null) {
            userToUpdate.setCountry(user.getCountry());
        }
        // Update other fields as needed
        return userRepository.save(userToUpdate);
    }

    @Override
    public void deleteUser(Long id) throws Exception {
        if (!userRepository.existsById(id)) {
            throw new Exception("User not found");
        }
        userRepository.deleteById(id);
    }
}