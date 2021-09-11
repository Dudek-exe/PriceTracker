package com.mypricetracker.pricetracker.scrapper.impl;

import com.mypricetracker.pricetracker.domain.product.ProductEntity;
import com.mypricetracker.pricetracker.scrapper.Scrapper;
import com.mypricetracker.pricetracker.scrapper.ScrapperTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Slf4j
@Component
public class XKomPriceScrapper extends Scrapper {

    private static final ScrapperTypeEnum scrapperTypeEnum = ScrapperTypeEnum.XKOM;

    @Override
    public ScrapperTypeEnum getScrapperTypeEnum() {
        return scrapperTypeEnum;
    }

    @Override
    public ProductEntity scrapFromUrl(String url) {
        try {
            Document document = Jsoup.connect(url).get();

            //Was "h1.sc-1bker4h-4.driGYx" but changed to "h1.sc-1bker4h-4"
            String name = scrapStringField(document, "h1.sc-1bker4h-4");

            //Was "div.u7xnnm-4.jFbqvs but changed to "div.u7xnnm-4"
            BigDecimal price = scrapPriceField(document, "div.u7xnnm-4");
            OffsetDateTime priceTime = OffsetDateTime.now();

            log.info("Successfully received data of product: Title: " + name + " price: " + price + " at: " + priceTime);
            return createProduct(name, price, priceTime, this.getScrapperTypeEnum());

        } catch (IOException e) {
            log.info("Unable to connect to requested URL: " + url);
            return null;
        }

    }


}
