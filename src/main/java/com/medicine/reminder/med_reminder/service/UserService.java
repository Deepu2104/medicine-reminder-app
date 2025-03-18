package com.medicine.reminder.med_reminder.service;

import com.medicine.reminder.med_reminder.dto.UserDTO;
import com.medicine.reminder.med_reminder.entity.User;

import java.util.List;
import java.util.Optional;


public interface UserService {

    void registerUser(UserDTO userDTO); // Create a new user

    Optional<User> findByEmail(String email); // Find user by email

    Optional<User> findByUsername(String username); // Find user by username

    boolean existsByEmail(String email); // Check if email exists

    boolean existsByUsername(String username); // Check if username exists

    List<User> getAllUsers(); // New method to fetch all users
}
