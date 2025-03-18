package com.medicine.reminder.med_reminder.controller;

import com.medicine.reminder.med_reminder.dto.UserDTO;
import com.medicine.reminder.med_reminder.entity.User;
import com.medicine.reminder.med_reminder.exception.UserException;
import com.medicine.reminder.med_reminder.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //  Register User
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserDTO userDTO) {
        System.out.println("Reached here!");
        if (userService.existsByEmail(userDTO.getEmail())) {
            throw new UserException("Email already exists");
        }
        if (userService.existsByUsername(userDTO.getUsername())) {
            throw new UserException("Username already exists");
        }
        userService.registerUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }

    // Get User by Email
    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        User user = userService.findByEmail(email)
                .orElseThrow(() -> new UserException("User with email " + email + " not found"));
        return ResponseEntity.ok(user);
    }

    // Get User by Username
    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new UserException("User with username " + username + " not found"));
        return ResponseEntity.ok(user);
    }

    // Get All Users
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            throw new UserException("No users found");
        }
        return ResponseEntity.ok(users);
    }
}
