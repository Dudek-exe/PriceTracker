package com.mypricetracker.pricetracker.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ProductResponse {

    private List<SingleProductData> responseProductList;

}
