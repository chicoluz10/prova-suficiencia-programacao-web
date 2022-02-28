package com.example.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "PRODUCT")
public class Product implements Serializable {

    @EmbeddedId
    private ProductID id;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "product_brand", nullable = false)
    private Long productBrand;

    @Column(name = "price", nullable = false)
    private Double price;

}
