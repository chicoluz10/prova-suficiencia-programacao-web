package com.example.repository;

import com.example.domain.Discount;
import com.example.domain.DiscountID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiscountRepository extends JpaRepository<Discount, DiscountID> {

    List<Discount> findAllByIdMembershipType(Long membershipType);
}
