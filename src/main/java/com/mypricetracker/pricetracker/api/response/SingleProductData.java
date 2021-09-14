package com.mypricetracker.pricetracker.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.OffsetDateTime;

@AllArgsConstructor
@Getter
public class SingleProductData {


    private final String productName;

    private final String productPrice;

    private final OffsetDateTime priceDate;

    private final String borderPrice;

    private final String shopType;
}

