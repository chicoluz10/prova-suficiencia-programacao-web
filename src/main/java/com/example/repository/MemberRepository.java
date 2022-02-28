package com.example.repository;

import com.example.domain.Member;
import com.example.domain.MemberID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, MemberID> {

    Optional<Member> findByUserId(Long userId);
}
