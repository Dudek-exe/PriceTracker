package com.mypricetracker.pricetracker.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@AllArgsConstructor
@Getter
public class SingleProductData {

    private final String productName;
    private final BigDecimal productPrice;
    private final OffsetDateTime priceDate;
    private final BigDecimal borderPrice;

}


