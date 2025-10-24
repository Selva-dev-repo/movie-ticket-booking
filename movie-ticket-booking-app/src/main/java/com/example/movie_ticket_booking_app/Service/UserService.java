package com.example.movie_ticket_booking_app.Service;
import java.util.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.movie_ticket_booking_app.Model.Users;
import com.example.movie_ticket_booking_app.DTO.*;
import com.example.movie_ticket_booking_app.Repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
//    public String loginUser(String userName, String password) {
//        Users user = userRepository.findByUserName(userName).orElse(null);
//
//        if (user == null) {
//            return "User not found";
//        }
//
//        if (passwordEncoder.matches(password, user.getPassword())) {
////        if(user.getPassword().equals(password)) {
//            return "Login successful";
//        } else {
//            return "Invalid password";
//        }
//    }
    
    public AuthResponseDTO loginUser(AuthRequestDTO loginRequest) {
        Users user = userRepository.findByUserName(loginRequest.getUserName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        }

        return new AuthResponseDTO("Login successful", user.getUserName(), user.getRole());
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
    	user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    
//    public Users updateUser(Long id, Users userDetails) {
//        Users user = getUserById(id);
//        
//        if ("admin".equals(userDetails.getRole()) && !user.getUserName().equals("admin") && userRepository.findByUserName("admin").isPresent()) {
//            throw new IllegalArgumentException("Cannot assign admin role; admin already exists");
//        }
//        user.setUserName(userDetails.getUserName());
//        user.setPassword(userDetails.getPassword());
//        user.setRole(userDetails.getRole());
//        user.setMobileNumber(userDetails.getMobileNumber());
//        user.setAddress(userDetails.getAddress());
//        user.setPincode(userDetails.getPincode());
//        user.setCity(userDetails.getCity());
//        user.setState(userDetails.getState());
//        user.setCountry(userDetails.getCountry());
//        return userRepository.save(user);
//    }
    
    public Users updateUser(Long id, Users updatedData) {
        Users existingUser = userRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with ID: " + id));

        if (updatedData.getUserName() != null) {
            existingUser.setUserName(updatedData.getUserName());
        }
        if (updatedData.getRole() != null) {
            existingUser.setRole(updatedData.getRole());
        }
        if (updatedData.getAddress() != null) {
            existingUser.setAddress(updatedData.getAddress());
        }
        if (updatedData.getCity() != null) {
            existingUser.setCity(updatedData.getCity());
        }
        if (updatedData.getCountry() != null) {
            existingUser.setCountry(updatedData.getCountry());
        }
        if (updatedData.getMobileNumber() != null) {
            existingUser.setMobileNumber(updatedData.getMobileNumber());
        }
        if (updatedData.getPincode() != null) {
            existingUser.setPincode(updatedData.getPincode());
        }
        if (updatedData.getState() != null) {
            existingUser.setState(updatedData.getState());
        }

        return userRepository.save(existingUser);
    }

    public void deleteUser(Long id) {
    	Users user = getUserById(id);
        if (user != null && "admin".equals(user.getRole())) {
            throw new IllegalArgumentException("Cannot delete admin user");
        }
        userRepository.deleteById(id);
    }
}

