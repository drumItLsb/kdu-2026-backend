package com.example.talentPortal;

import com.example.talentPortal.config.JwtService;
import com.example.talentPortal.dto.*;
import com.example.talentPortal.entity.User;
import com.example.talentPortal.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserResponse register(@Valid @RequestBody RegisterRequest request) {
        return userService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword())
        );

        UserDetails principal = (UserDetails) authentication.getPrincipal();
        assert principal != null;
        String token = jwtService.generateToken(principal.getUsername(), principal.getAuthorities());

        // Get roles (either from principal authorities or from DB)
        User user = userService.getByUserName(principal.getUsername());
        List<String> roles = user.getRoles();

        return new AuthResponse(token, user.getUserName(), roles);
    }
}

