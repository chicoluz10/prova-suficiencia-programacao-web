package com.example.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "BRAND")
public class Brand implements Serializable {

    @EmbeddedId
    private BrandID id;

    @Column(name = "brand_name", nullable = false)
    private String brandName;
}
