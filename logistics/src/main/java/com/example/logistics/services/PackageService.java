package com.example.logistics.services;

import com.example.logistics.entity.Package;
import com.example.logistics.repository.PackageRepository;
import com.example.logistics.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;

@Service
public class PackageService {
    private final PackageRepository packageRepository;
    private final ExecutorService executorService;

    public PackageService(PackageRepository packageRepository, ExecutorService executorService) {
        this.packageRepository = packageRepository;
        this.executorService = executorService;
    }

    public Package addPackage(Package pack) {
        try {
            Package p = packageRepository.save(pack);
            executorService.submit(() -> processPackageAddition(p.getId()));
            return p;
        } catch(DataIntegrityViolationException e) {
            // DataIntegrityViolationException: indicates database error like not unique, adding null
            throw new RuntimeException("Database error: "+ e.getMessage());
        }
    }

    public void processPackageAddition(String id) {
        try {
            Thread.sleep(3000);
            Package p = packageRepository
                    .findById(id).orElseThrow(() -> new RuntimeException("package not found"));
            p.setStatus("SORTED");
            packageRepository.save(p);
        } catch (InterruptedException e) {}
    }

    public int findTotalRevenue() {
        return packageRepository.calculateTotalProjectRevenue();
    }
}
