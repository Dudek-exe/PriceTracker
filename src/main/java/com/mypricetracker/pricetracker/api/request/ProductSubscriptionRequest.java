package com.mypricetracker.pricetracker.api.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class ProductSubscriptionRequest {

    private String url;

    @Nullable
    private BigDecimal borderPrice;

}
