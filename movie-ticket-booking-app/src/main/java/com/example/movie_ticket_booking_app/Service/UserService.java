package com.example.movie_ticket_booking_app.Service;

import java.util.List;
import com.example.movie_ticket_booking_app.DTO.*;
import com.example.movie_ticket_booking_app.Model.Users;

public interface UserService {
	AuthResponseDTO loginUser(AuthRequestDTO loginRequest) throws Exception;
    List<Users> getAllUsers();
    Users getUserById(Long id);
    Users createUser(Users user) throws Exception;
    Users updateUser(Long id, Users user) throws Exception;
    void deleteUser(Long id) throws Exception;
}
