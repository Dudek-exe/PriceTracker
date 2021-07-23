package com.mypricetracker.pricetracker.scrapper;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

@Getter
@Component
public class ScrapperContainer {

    Map<ScrapperTypeEnum, Scrapper> scrapperMap = new EnumMap<>(ScrapperTypeEnum.class);

    XKomPriceScrapper xKomPriceScrapper;


    public ScrapperContainer(XKomPriceScrapper xKomPriceScrapper) {
        this.xKomPriceScrapper = xKomPriceScrapper;

        scrapperMap.put(ScrapperTypeEnum.XKOM, xKomPriceScrapper);
    }

}
