package com.example.DeviceManagementSystem.service;

import com.example.DeviceManagementSystem.dto.RegisterRequestDTO;
import com.example.DeviceManagementSystem.dto.UserResponseDTO;
import com.example.DeviceManagementSystem.entity.User;
import com.example.DeviceManagementSystem.exception.ResourceAlreadyExistsException;
import com.example.DeviceManagementSystem.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDTO register(RegisterRequestDTO req) {
        if (userRepository.existsByUserName(req.getUserName())) {
            throw new ResourceAlreadyExistsException("Username already exists");
        }
        if (userRepository.existsByEmail(req.getEmail())) {
            throw new ResourceAlreadyExistsException("Email already exists");
        }
        String hashedPassword = passwordEncoder.encode(req.getPassword());

        User user = new User(req.getEmail(),hashedPassword, req.getUserName());
        User savedUser = userRepository.save(user);

        return new UserResponseDTO(savedUser.getId(),req.getUserName(), req.getEmail());
    }

    public User getByUserName(String userName) {
        return userRepository.findByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + userName));
    }
}