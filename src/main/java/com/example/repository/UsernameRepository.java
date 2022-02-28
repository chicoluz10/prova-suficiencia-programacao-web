package com.example.repository;

import com.example.domain.Username;
import com.example.domain.UsernameId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsernameRepository extends JpaRepository<Username, UsernameId> {

    Optional<Username> findByIdUsername(String username);
}
