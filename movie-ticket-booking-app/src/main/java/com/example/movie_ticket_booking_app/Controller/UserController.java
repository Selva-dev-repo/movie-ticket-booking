package com.example.movie_ticket_booking_app.Controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.example.movie_ticket_booking_app.Model.Users;
import com.example.movie_ticket_booking_app.Service.UserService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Users user) {
        String result = userService.loginUser(user.getUserName(), user.getPassword());
        if (result.equals("Login successful")) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.status(401).body(result);
    }

    @GetMapping("/users")
    public List<Users> getUsers() {
        return userService.getAllUsers();
    }
    
    @GetMapping("/users/{id}")
    public Users getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public Users createUser(@RequestBody Users user) {
        return userService.createUser(user);
    }
    
    @PutMapping("/users/{id}")
    public Users updateUser(@PathVariable Long id, @RequestBody Users user) {
        return userService.updateUser(id, user);
    }
    
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}

