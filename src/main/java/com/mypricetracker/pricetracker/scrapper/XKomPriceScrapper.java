package com.mypricetracker.pricetracker.scrapper;

import com.mypricetracker.pricetracker.domain.product.ProductEntity;
import com.mypricetracker.pricetracker.exception.ScrappingFieldException;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Component
public class XKomPriceScrapper implements Scrapper{

    private final ScrapperTypeEnum scrapperTypeEnum = ScrapperTypeEnum.XKOM;

    public ScrapperTypeEnum getScrapperTypeEnum() {
        return scrapperTypeEnum;
    }

    @Override
    public ProductEntity scrapFromUrl(String url) {
        try {
            Document document = Jsoup.connect(url).get();
            ProductEntity product = new ProductEntity();

            //Was "h1.sc-1bker4h-4.driGYx" but changed to "h1.sc-1bker4h-4"
            String name = scrapStringField(document, "h1.sc-1bker4h-4");

            //Was "div.u7xnnm-4.jFbqvs but changed to "div.u7xnnm-4"
            BigDecimal price = scrapPriceField(document, "div.u7xnnm-4");
            OffsetDateTime priceTime = OffsetDateTime.now();

            product.setProductName(name);
            product.setProductPrice(price);
            product.setPriceDate(priceTime);

            log.info("Successfully received data of product: Title: " + name + " price: " + price + " at: " + priceTime);
            return product;

        } catch (IOException e) {
            log.info("Unable to connect to requested URL: " + url);
            return null;
        }

    }

    private String scrapStringField(Document document, String field) {
        Element tempElement = document.select(field).first();
        if (tempElement != null) {
            return tempElement.select(field).text();
        } else {
            throw new ScrappingFieldException("Could not scrap field: " + field);
        }
    }

    private BigDecimal scrapPriceField(Document document, String field) {
        Element tempElement = document.select(field).first();
        if (tempElement != null) {
            String price = tempElement.select(field).text();
            price = convertOriginalPrice(price);
            return new BigDecimal(price);
        } else {
            throw new ScrappingFieldException("Could not scrap field: " + field);
        }
    }

    private String convertOriginalPrice(String price) {
        return price.replace("zł", "").replace(",", ".").replace(" ", "");
    }

}
