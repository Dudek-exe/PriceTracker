package com.mypricetracker.pricetracker.domain.product;

import com.mypricetracker.pricetracker.scrapper.Scrapper;

public interface ScrappingService {

    Product scrapDataFromUrl(Scrapper scrapper, String url);


}
