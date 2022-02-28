package com.example.repository;

import com.example.domain.Stock;
import com.example.domain.StockID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, StockID> {
}
