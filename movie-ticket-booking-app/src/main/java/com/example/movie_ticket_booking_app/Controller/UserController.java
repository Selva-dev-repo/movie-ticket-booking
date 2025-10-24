package com.example.movie_ticket_booking_app.Controller;
import org.springframework.http.HttpStatus;
import java.util.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.example.movie_ticket_booking_app.Model.Users;
import com.example.movie_ticket_booking_app.DTO.*;
import com.example.movie_ticket_booking_app.Service.UserService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    
//    @PostMapping("/login")
//    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> loginRequest) {
//        String userName = loginRequest.get("userName");
//        String password = loginRequest.get("password");
//
//        String result = userService.loginUser(userName, password);
//
//        Map<String, Object> response = new HashMap<>();
//        response.put("message", result);
//
//        if ("Login successful".equals(result)) {
//            Users user = userService.getUserByUserName(userName);
//            user.setPassword(null);
//            response.put("user", user);
//            return ResponseEntity.ok(response);
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
//        }
//    }
    
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO loginRequest) {
        AuthResponseDTO response = userService.loginUser(loginRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/users")
    public List<Users> getUsers() {
        return userService.getAllUsers();
    }
    
    @GetMapping("/users/{id}")
    public Users getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/users")
    public Users createUser(@RequestBody Users user) {
        return userService.createUser(user);
    }
    
//    @PutMapping("/users/{id}")
//    public Users updateUser(@PathVariable Long id, @RequestBody Users user) {
//        return userService.updateUser(id, user);
//    }
    
    @PutMapping("/users/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable Long id, @RequestBody Users user) {
        try {
            Users updatedUser = userService.updateUser(id, user);
            return ResponseEntity.ok(updatedUser);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}

