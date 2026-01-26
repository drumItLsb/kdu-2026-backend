package com.example.DeviceManagementSystem.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String greet() {
        return "Welcome to kickdrum Device management system";
    }
}
