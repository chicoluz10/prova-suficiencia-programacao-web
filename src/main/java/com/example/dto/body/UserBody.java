package com.example.dto.body;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserBody {

    private String fullName;

    private String username;

    private LocalDate birthDate;

    private Long membership;
}
