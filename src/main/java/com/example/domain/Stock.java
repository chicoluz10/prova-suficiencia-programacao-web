package com.example.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "STOCK")
public class Stock implements Serializable {

    @EmbeddedId
    private StockID id;

    @Column(name = "quantity", nullable = false)
    private Long quantity;
}
