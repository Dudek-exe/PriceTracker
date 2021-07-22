package com.mypricetracker.pricetracker.domain.product;

import com.mypricetracker.pricetracker.scrapper.Scrapper;

import java.math.BigDecimal;

public interface ScrappingService {

    void scrapDataFromUrlWithoutBorderPrice(Scrapper scrapper, String url);

    void scrapDataFromUrlWithBorderPrice(Scrapper scrapper, String url, BigDecimal borderPrice);
}
