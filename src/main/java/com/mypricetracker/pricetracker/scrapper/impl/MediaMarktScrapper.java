package com.mypricetracker.pricetracker.scrapper.impl;

import com.mypricetracker.pricetracker.config.SSLHelper;
import com.mypricetracker.pricetracker.domain.product.ProductEntity;
import com.mypricetracker.pricetracker.scrapper.Scrapper;
import com.mypricetracker.pricetracker.scrapper.ScrapperTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Slf4j
@Component
public class MediaMarktScrapper extends Scrapper {

    private static final ScrapperTypeEnum scrapperTypeEnum = ScrapperTypeEnum.MEDIA_MARKT;

    @Override
    public ScrapperTypeEnum getScrapperTypeEnum() {
        return scrapperTypeEnum;
    }

    @Override
    public ProductEntity scrapFromUrl(String url) {
        try {
            Document document = SSLHelper.getConnection(url).userAgent(HttpHeaders.USER_AGENT).get();
            ProductEntity product = new ProductEntity();

            String name = scrapStringField(document, "h1.b-ofr_headDataTitle");

            BigDecimal price = scrapPriceField(document, "div.b-contentSideBox div.m-priceBox_price");
            OffsetDateTime priceTime = OffsetDateTime.now();

            product.setProductName(name);
            product.setProductPrice(price);
            product.setPriceDate(priceTime);

            log.info("Successfully received data of product: Title: " + name + " price: " + price + " at: " + priceTime);
            return product;

        } catch (IOException e) {
            log.info("Unable to connect to requested URL: " + url);
            e.printStackTrace();
            return null;
        }

    }

}