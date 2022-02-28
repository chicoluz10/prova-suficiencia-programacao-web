package com.example.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {

    private String brandName;
    private String productName;
    private Double productPrice;
    private Long discountPercentage;
    private Double finalPrice;

    public ProductDTO(String brandName, String productName, Double productPrice, Long discountPercentage, Double finalPrice) {
        this.brandName = brandName;
        this.productName = productName;
        this.productPrice = productPrice;
        this.discountPercentage = discountPercentage;
        this.finalPrice = finalPrice;
    }
}
