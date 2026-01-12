package com.example.logistics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.logistics.entity.Package;
import org.springframework.data.jpa.repository.Query;


public interface PackageRepository extends JpaRepository<Package, String> {
    @Query(value = "SELECT sum(weight * 2.5) as revenue FROM package where status = 'sorted'", nativeQuery = true)
    int calculateTotalProjectRevenue();
}