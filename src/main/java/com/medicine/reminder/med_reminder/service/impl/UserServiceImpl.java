package com.medicine.reminder.med_reminder.service.impl;

import com.medicine.reminder.med_reminder.dto.UserDTO;
import com.medicine.reminder.med_reminder.entity.User;
import com.medicine.reminder.med_reminder.enums.Role;
import com.medicine.reminder.med_reminder.repository.UserRepository;
import com.medicine.reminder.med_reminder.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void registerUser(UserDTO userDTO) {
        Boolean value = userRepository.existsByEmail(userDTO.getEmail());
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new RuntimeException("Email is already registered");
        }
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new RuntimeException("Username is already taken");
        }

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword())); // Encrypt password
        user.setRole(Role.USER); // Default role: USER
        user.setAge(userDTO.getAge());
        user.setGender(userDTO.getGender());
        user.setPhoneNumber(userDTO.getPhoneNumber());

        userRepository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}
