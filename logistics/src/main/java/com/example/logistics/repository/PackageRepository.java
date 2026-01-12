package com.example.logistics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.logistics.entity.Package;


public interface PackageRepository extends JpaRepository<Package, String> {
}