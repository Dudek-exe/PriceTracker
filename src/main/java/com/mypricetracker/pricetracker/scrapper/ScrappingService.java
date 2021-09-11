package com.mypricetracker.pricetracker.scrapper;

import com.mypricetracker.pricetracker.api.response.SingleProductData;
import com.mypricetracker.pricetracker.domain.product.ProductEntity;

import java.math.BigDecimal;
import java.util.List;

public interface ScrappingService {

    ProductEntity scrapDataFromUrlWithoutBorderPrice(Scrapper scrapper, String url);

    ProductEntity scrapDataFromUrlWithBorderPrice(Scrapper scrapper, String url, BigDecimal borderPrice);

    List<SingleProductData> getAllPricesForProduct(String name);
}
