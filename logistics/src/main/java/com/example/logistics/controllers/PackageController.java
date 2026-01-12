package com.example.logistics.controllers;

import com.example.logistics.entity.Package;
import com.example.logistics.repository.PackageRepository;
import com.example.logistics.services.PackageService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/packages")
public class PackageController {
    private final PackageService packageService;

    public PackageController(PackageService packageService) {
        this.packageService = packageService;
    }

    @PostMapping
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<Package> addPackage(@Valid @RequestBody Package book) {
        Package createdPackage = packageService.addPackage(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPackage);
    }

    @GetMapping("/analytics/revenue")
    public ResponseEntity<Map<String,String>> getTotalRevenue() {
        Map<String,String> result = new HashMap<>();
        int revenue = packageService.findTotalRevenue();
        result.put("status","successfull");
        result.put("revenue",revenue+"");
        return ResponseEntity.ok(result);
    }
}
