package com.mypricetracker.pricetracker.domain.product;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Data
@NoArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Name", nullable = false, length = 200)
    private String productName;

    @Column(name = "Price",nullable = false)
    private BigDecimal productPrice;

    //TODO consider changing to OffsetDateTime
    @Column(name = "Price_time", nullable = false,columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime priceDate;

    //price set by user when is interested in buying
    @Column(name = "Border_price")
    private BigDecimal borderPrice;

}
