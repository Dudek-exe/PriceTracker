package com.mypricetracker.pricetracker.api.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;

@Getter
@RequiredArgsConstructor
public class ProductSubscriptionRequest {

    private final String url;

    @Nullable
    private BigDecimal borderPrice;

}
