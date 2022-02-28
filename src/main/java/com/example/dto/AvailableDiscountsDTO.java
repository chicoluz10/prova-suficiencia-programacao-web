package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AvailableDiscountsDTO {

    private String fullName;
    private String membershipName;

    public AvailableDiscountsDTO(String fullName, String membershipName) {
        this.fullName = fullName;
        this.membershipName = membershipName;
    }

    private List<ProductDTO> products;
}
