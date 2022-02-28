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
public class BrandID implements Serializable {

    @Column(name = "brand_id", nullable = false)
    private Long brandId;
}
