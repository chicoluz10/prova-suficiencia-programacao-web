package com.example.repository;

import com.example.domain.Brand;
import com.example.domain.BrandID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, BrandID> {
}
