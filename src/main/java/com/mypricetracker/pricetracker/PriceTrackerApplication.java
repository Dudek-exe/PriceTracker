package com.mypricetracker.pricetracker;

import com.mypricetracker.pricetracker.scrapper.XKomPriceScrapper;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PriceTrackerApplication {

    public static void main(String[] args) {
        //SpringApplication.run(PriceTrackerApplication.class, args);
    XKomPriceScrapper xKomPriceScrapper = new XKomPriceScrapper();

    xKomPriceScrapper.scrapFromUrl("https://www.x-kom.pl/p/648711-smartfon-telefon-apple-iphone-12-128gb-purple-5g.html");

    }

}
