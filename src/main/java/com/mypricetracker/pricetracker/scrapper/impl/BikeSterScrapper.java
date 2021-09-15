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
import java.time.OffsetDateTime;

@Slf4j
@Component
class BikeSterScrapper extends Scrapper {

    private static final ScrapperTypeEnum scrapperTypeEnum = ScrapperTypeEnum.BIKESTER;

    @Override
    public ScrapperTypeEnum getScrapperTypeEnum() {
        return scrapperTypeEnum;
    }

    @Override
    public ProductEntity scrapFromUrl(String url) {
        try {
            //Application is able to scrap from MediaMarkt site only when SSL is ignored
            Document document = SSLHelper.getConnection(url).userAgent(HttpHeaders.USER_AGENT).get();
            BigDecimal price;

            String name = scrapStringField(document, "h1.cyc-typo_display-3.cyc-margin_bottom-1.cyc-no-texttransform");

            BigDecimal promoPrice = scrapPriceField(document, "div.cyc-margin_right-2.cyc-typo_display-3.cyc-color-text_sale");
            BigDecimal oroginalPrice = scrapPriceField(document, "div.cyc-margin_right-2.cyc-typo_display-3");
            OffsetDateTime priceTime = OffsetDateTime.now();

            if (promoPrice != null) {
                price = promoPrice;
            } else {
                price = oroginalPrice;
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

    @Override
    protected String convertOriginalPrice(String price) {
        price = price.replaceAll("[^\\d,.]", "");
        price = price.replace(".", "");
        return price;
    }

}