package com.example.domain;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "MEMBER")
public class Member implements Serializable {

    @EmbeddedId
    private MemberID id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "membership_date", nullable = false)
    private LocalDate membershipDate;

    @Column(name = "membership_type", nullable = false)
    private Long membershipType;

    @Column(name = "membership_status", nullable = false)
    private String membershipStatus;
}
