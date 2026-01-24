package com.example.SkySeatFlightBooking.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String greet() {
        return "Hello welcome to flight management system!!!";
    }
}