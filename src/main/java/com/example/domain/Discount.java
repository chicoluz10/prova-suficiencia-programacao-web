package com.example.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "DISCOUNT")
public class Discount implements Serializable {

    @EmbeddedId
    DiscountID id;

    @Column(name = "percentage", nullable = false)
    private Long percentage;
}
