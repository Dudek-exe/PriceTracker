package com.mypricetracker.pricetracker.scrapper.impl;

import com.mypricetracker.pricetracker.scrapper.Scrapper;
import com.mypricetracker.pricetracker.scrapper.ScrapperTypeEnum;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

@Getter
@Component
public class ScrapperContainer {

    Map<ScrapperTypeEnum, Scrapper> scrapperMap = new EnumMap<>(ScrapperTypeEnum.class);

    XKomPriceScrapper xKomPriceScrapper;
    MediaMarktScrapper mediaMarktScrapper;
    JulaScrapper julaScrapper;


    public ScrapperContainer(XKomPriceScrapper xKomPriceScrapper, MediaMarktScrapper mediaMarktScrapper, JulaScrapper julaScrapper) {
        this.xKomPriceScrapper = xKomPriceScrapper;
        this.mediaMarktScrapper = mediaMarktScrapper;
        this.julaScrapper = julaScrapper;

        scrapperMap.put(xKomPriceScrapper.getScrapperTypeEnum(), xKomPriceScrapper);
        scrapperMap.put(mediaMarktScrapper.getScrapperTypeEnum(), mediaMarktScrapper);
        scrapperMap.put(julaScrapper.getScrapperTypeEnum(), julaScrapper);
    }

}
