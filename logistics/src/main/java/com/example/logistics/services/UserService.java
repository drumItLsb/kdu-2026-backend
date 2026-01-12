package com.example.logistics.services;

import com.example.logistics.dto.RegisterRequest;
import com.example.logistics.dto.UserResponse;
import com.example.logistics.entity.User;
import com.example.logistics.exception.ResourceAlreadyExistsException;
import com.example.logistics.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse register(RegisterRequest req) {
        if (userRepository.existsByUserName(req.getUserName())) {
            throw new ResourceAlreadyExistsException("Username already exists");
        }
        if (userRepository.existsByEmail(req.getEmail())) {
            throw new ResourceAlreadyExistsException("Email already exists");
        }

        List<String> roles = (req.getRoles() == null || req.getRoles().isEmpty())
                ? List.of("ROLE_DRIVER")
                : req.getRoles();

        // IMPORTANT: encrypt password before saving
        String hashedPassword = passwordEncoder.encode(req.getPassword());

        User user = new User(req.getUserName(), hashedPassword, req.getEmail(), roles);
        User saved = userRepository.save(user);

        return new UserResponse(saved.getUserName(), saved.getEmail(), saved.getRoles());
    }

    public User getByUserName(String userName) {
        return userRepository.findByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + userName));
    }
}