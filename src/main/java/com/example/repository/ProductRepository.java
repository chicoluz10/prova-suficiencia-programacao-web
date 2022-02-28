package com.example.repository;

import com.example.domain.Product;
import com.example.domain.ProductID;
import com.example.dto.ProductDTO;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Registered
public interface ProductRepository extends JpaRepository<Product, ProductID> {

    List<Product> findAllByProductNameIn(List<String> productNames);
}
