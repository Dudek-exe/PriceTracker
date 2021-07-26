package com.mypricetracker.pricetracker.api.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class NewProductRequest {

    private String url;
    private BigDecimal borderPrice;

}
