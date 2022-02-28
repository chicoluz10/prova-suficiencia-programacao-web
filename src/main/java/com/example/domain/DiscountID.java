package com.example.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class DiscountID implements Serializable {

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "membership_type", nullable = false)
    private Long membershipType;
}
