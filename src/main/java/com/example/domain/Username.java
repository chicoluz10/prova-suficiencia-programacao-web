package com.example.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "username")
public class Username {
    @EmbeddedId
    private UsernameId id;
}