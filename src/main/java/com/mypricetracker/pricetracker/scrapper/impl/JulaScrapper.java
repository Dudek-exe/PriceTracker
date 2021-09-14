package com.mypricetracker.pricetracker.scrapper.impl;

import com.mypricetracker.pricetracker.config.SSLHelper;
import com.mypricetracker.pricetracker.domain.product.ProductEntity;
import com.mypricetracker.pricetracker.scrapper.Scrapper;
import com.mypricetracker.pricetracker.scrapper.ScrapperTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;

@Slf4j
@Component
public class JulaScrapper extends Scrapper {

    private static final ScrapperTypeEnum scrapperTypeEnum = ScrapperTypeEnum.JULA;

    @Override
    public ScrapperTypeEnum getScrapperTypeEnum() {
        return scrapperTypeEnum;
    }

    @Override
    public ProductEntity scrapFromUrl(String url) {
        try {
            //Application is able to scrap from MediaMarkt site only when SSL is ignored
            Document document = SSLHelper.getConnection(url).userAgent(HttpHeaders.USER_AGENT).get();

            String name = scrapStringField(document, "div.col-xs-8");

            BigDecimal price = scrapPriceField(document, "div.p-product-detail__price div.c-price-tag__price");
            OffsetDateTime priceTime = OffsetDateTime.now();
            BigDecimal decimalPart = scrapPriceField(document, "div.p-product-detail__price div.c-price-tag__price span.c-price-tag__decimals");

            if (decimalPart != null) {
                decimalPart = decimalPart.divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
                price = price.add(decimalPart);
            }

            log.info("Successfully received data of product: Title: " + name + " price: " + price + " at: " + priceTime);
            return createProduct(name, price, priceTime, this.getScrapperTypeEnum());

        } catch (IOException e) {
            log.info("Unable to connect to requested URL: " + url);
            e.printStackTrace();
            return null;
        }

    }

    @Override
    protected BigDecimal scrapPriceField(Document document, String field) {
        Element tempElement = document.select(field).first();
        if (tempElement != null) {
            String price = tempElement.ownText();
            price = convertOriginalPrice(price);
            return new BigDecimal(price);
        } else {
            return null;
        }
    }

}