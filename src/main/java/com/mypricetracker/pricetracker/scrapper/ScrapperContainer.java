package com.mypricetracker.pricetracker.scrapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

@Getter
@Component
public class ScrapperContainer {

    Map<ScrapperTypeEnum, Scrapper> scrapperMap = new HashMap<>();

    XKomPriceScrapper xKomPriceScrapper = new XKomPriceScrapper();


    public ScrapperContainer(XKomPriceScrapper xKomPriceScrapper) {
        this.xKomPriceScrapper = xKomPriceScrapper;

        scrapperMap.put(ScrapperTypeEnum.XKOM, xKomPriceScrapper);
    }

}
