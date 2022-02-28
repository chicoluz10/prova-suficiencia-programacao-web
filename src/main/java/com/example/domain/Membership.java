package com.example.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "memberships")
public class Membership {

    @EmbeddedId
    private MembershipID id;

    @Column(name = "membership_name", nullable = false, length = 64)
    private String membershipName;

    @Column(name = "membership_description")
    private String membershipDescription;
}