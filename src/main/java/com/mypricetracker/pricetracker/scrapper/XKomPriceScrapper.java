package com.mypricetracker.pricetracker.scrapper;

import com.mypricetracker.pricetracker.exception.ScrappingFieldException;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

@Slf4j
public class XKomPriceScrapper implements Scrapper{

    public void scrapFromUrl(String url) {
        try {
            Document document = Jsoup.connect(url).get();

            String title = scrapStringField(document, "h1.sc-1bker4h-4.driGYx");
            Double price = scrapPriceField(document, "div.u7xnnm-4.jFbqvs");

            log.info("Successfully received data of product. Title: " + title + " price: " + price);

        } catch (IOException e) {
            log.info("Unable to connect to requested URL: " + url);
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

    private Double scrapPriceField(Document document, String field) {
        Element tempElement = document.select(field).first();
        if (tempElement != null) {
            String price = tempElement.select(field).text();
            price = convertOriginalPrice(price);
            return Double.valueOf(price);
        } else {
            throw new ScrappingFieldException("Could not scrap field: " + field);
        }
    }

    private String convertOriginalPrice(String price) {
        return price.replace("zł", "").replace(",", ".").replace(" ", "");
    }

}
