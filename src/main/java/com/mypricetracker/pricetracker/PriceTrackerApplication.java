package com.mypricetracker.pricetracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PriceTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PriceTrackerApplication.class, args);
/*
    XKomPriceScrapper xKomPriceScrapper = new XKomPriceScrapper();

    xKomPriceScrapper.scrapFromUrl("https://www.x-kom.pl/p/597339-karta-graficzna-nvidia-gigabyte-geforce-rtx-3070-gaming-oc-8gb-gddr6.html");
*/

    }

}
