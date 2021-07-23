package com.mypricetracker.pricetracker.scrapper;

import com.mypricetracker.pricetracker.domain.product.ProductEntity;

public interface Scrapper {

    ProductEntity scrapFromUrl(String url);

}
