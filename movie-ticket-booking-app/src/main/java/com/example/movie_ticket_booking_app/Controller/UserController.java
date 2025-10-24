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
    
//    @PostMapping("/login")
//    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO loginRequest) {
//        AuthResponseDTO response = userService.loginUser(loginRequest);
//        return ResponseEntity.ok(response);
//    }
    
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO loginRequest) {
        try {
            AuthResponseDTO response = userService.loginUser(loginRequest);
            if (response.isSuccess()) {
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
        } catch (Exception e) {
            AuthResponseDTO errorResponse = new AuthResponseDTO(false, "Internal server error", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

//    @GetMapping("/users")
//    public List<Users> getUsers() {
//        return userService.getAllUsers();
//    }
    
    @GetMapping("/users")
    public ResponseEntity<List<Users>> getUsers() {
        try {
            List<Users> users = userService.getAllUsers();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
//    @GetMapping("/users/{id}")
//    public Users getUser(@PathVariable Long id) {
//        return userService.getUserById(id);
//    }
    
    @GetMapping("/users/{id}")
    public ResponseEntity<Users> getUser(@PathVariable Long id) {
        try {
            Users user = userService.getUserById(id);
            if (user != null) {
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

//    @PostMapping("/users")
//    public Users createUser(@RequestBody Users user) {
//        return userService.createUser(user);
//    }
    
    @PostMapping("/users")
    public ResponseEntity<Users> createUser(@RequestBody Users user) {
    	try {
    		Users createdUser = userService.createUser(user);
    		return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    	} catch (Exception e) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    	}
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
    
//    @PutMapping("/users/{id}")
//    public ResponseEntity<Object> updateUser(@PathVariable Long id, @RequestBody Users user) {
//    	try {
//    		Users updatedUser = userService.updateUser(id, user);
//    		if (updatedUser != null) {
//    			return ResponseEntity.ok(updatedUser);
//    		} else {
//    			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//    		}
//    	} catch (Exception e) {
//    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//    	}
//    }
    
//    @DeleteMapping("/users/{id}")
//    public void deleteUser(@PathVariable Long id) {
//        userService.deleteUser(id);
//    }
    
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    	try {
    		userService.deleteUser(id);
    		return ResponseEntity.noContent().build();
    	} catch (Exception e) {
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    	}
    }
}

