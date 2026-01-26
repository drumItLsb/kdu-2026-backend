package com.example.DeviceManagementSystem.controller;

import com.example.DeviceManagementSystem.config.JwtService;
import com.example.DeviceManagementSystem.dto.*;
import com.example.DeviceManagementSystem.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);


    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserResponseDTO register(@Valid @RequestBody RegisterRequestDTO request) {
        return userService.register(request);
    }

    @PostMapping("/login")
    public AuthResponseDTO login(@Valid @RequestBody LoginRequestDTO request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword())
        );

        UserDetails principal = (UserDetails) authentication.getPrincipal();
        assert principal != null;
        String token = jwtService.generateToken(principal.getUsername(), principal.getAuthorities());


        return new AuthResponseDTO(token, principal.getUsername());
    }
}
