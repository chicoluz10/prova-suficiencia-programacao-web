package com.example.repository;

import com.example.domain.Membership;
import com.example.domain.MembershipID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, MembershipID> {
}
