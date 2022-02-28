package com.example.repository;

import com.example.domain.User;
import com.example.domain.UserID;
import com.example.dto.AvailableDiscountsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UserID> {
}
