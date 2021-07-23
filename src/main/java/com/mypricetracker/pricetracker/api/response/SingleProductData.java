package com.mypricetracker.pricetracker.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@AllArgsConstructor
@Getter
public class SingleProductData {

    private String productName;
    private BigDecimal productPrice;
    private OffsetDateTime priceDate;
    private BigDecimal borderPrice;

}


