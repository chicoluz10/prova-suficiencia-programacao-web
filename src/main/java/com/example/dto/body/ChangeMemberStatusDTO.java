package com.example.dto.body;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChangeMemberStatusDTO {

    private Long memberId;
    private Long userId;
    private String status;
}
