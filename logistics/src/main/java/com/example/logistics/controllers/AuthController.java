//package com.example.logistics.controllers;
//
//package com.example.talentPortal.controllers;
//
//import com.example.logistics.config.JwtService;
////import com.example.logistics.dto.*;
//import com.example.logistics.entity.User;
//import com.example.logistics.service.UserService;
//import jakarta.logistics.Valid;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.authentication.*;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/auth")
//public class AuthController {
//
//    private final AuthenticationManager authenticationManager;
//    private final JwtService jwtService;
//    private final UserService userService;
//    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
//
//
//    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService, UserService userService) {
//        this.authenticationManager = authenticationManager;
//        this.jwtService = jwtService;
//        this.userService = userService;
//    }
//
//    @PostMapping("/register")
//    public UserResponse register(@Valid @RequestBody RegisterRequest request) {
//        return userService.register(request);
//    }
//
//    @PostMapping("/login")
//    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword())
//        );
//
//        UserDetails principal = (UserDetails) authentication.getPrincipal();
//        assert principal != null;
//        String token = jwtService.generateToken(principal.getUsername(), principal.getAuthorities());
//
//        // Get roles (either from principal authorities or from DB)
//        User user = userService.getByUserName(principal.getUsername());
//        List<String> roles = user.getRoles();
//
//        log.info("User: {} Successfully logged in",user.getUserName());
//
//        return new AuthResponse(token, user.getUserName(), roles);
//    }
//}
