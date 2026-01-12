package com.example.logistics.services;

import com.example.logistics.entity.Package;
import com.example.logistics.repository.PackageRepository;
import com.example.logistics.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PackageService {
    private final PackageRepository packageRepository;

    public PackageService(PackageRepository packageRepository) {
        this.packageRepository = packageRepository;
    }

    public Package addPackage(Package book) {
        try {
            return packageRepository.save(book);
        } catch(DataIntegrityViolationException e) {
            // DataIntegrityViolationException: indicates database error like not unique, adding null
            throw new RuntimeException("Database error: "+ e.getMessage());
        }
    }

    public int findTotalRevenue() {
        return packageRepository.calculateTotalProjectRevenue();
    }
}
