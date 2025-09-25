package com.example.movie_ticket_booking_app.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.movie_ticket_booking_app.Model.Users;
import com.example.movie_ticket_booking_app.Service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<Users> getUsers() {
        return userService.getAllUsers();
    }
    
    @GetMapping("/{id}")
    public Users getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public Users createUser(@RequestBody Users user) {
        return userService.createUser(user);
    }
    
    @PutMapping("/{id}")
    public Users updateUser(@PathVariable Long id, @RequestBody Users user) {
        return userService.updateUser(id, user);
    }
    
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}

