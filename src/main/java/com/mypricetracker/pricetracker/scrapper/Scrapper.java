package com.mypricetracker.pricetracker.scrapper;

import com.mypricetracker.pricetracker.domain.product.Product;

public interface Scrapper {

    Product scrapFromUrl(String url);

}
