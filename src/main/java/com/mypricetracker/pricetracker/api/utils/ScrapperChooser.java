package com.mypricetracker.pricetracker.api.utils;

import com.mypricetracker.pricetracker.exception.NoSuchScrapperEnumTypeException;
import com.mypricetracker.pricetracker.scrapper.Scrapper;
import com.mypricetracker.pricetracker.scrapper.impl.ScrapperContainer;
import com.mypricetracker.pricetracker.scrapper.ScrapperTypeEnum;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class ScrapperChooser {

    private final ScrapperContainer scrapperContainer;

    public ScrapperChooser(ScrapperContainer scrapperContainer) {
        this.scrapperContainer = scrapperContainer;
    }

    public Scrapper extractScrapperFromUrl(String url) {
        url = url.replace("https://", "").replace("-", "");
        String scrapperTypeFromUrl = url.substring(0, 20).toUpperCase(Locale.ROOT);
        return scrapperContainer.getScrapperMap().get(ScrapperTypeEnum.from(chooseScrapperFromUrl(scrapperTypeFromUrl)));
    }

    //TODO add more cases
    private String chooseScrapperFromUrl(String scrapperTypeFromUrl) {
        if (scrapperTypeFromUrl.contains(ScrapperTypeEnum.XKOM.getScrapperType()))
            return ScrapperTypeEnum.XKOM.getScrapperType();

        if (scrapperTypeFromUrl.contains(ScrapperTypeEnum.MEDIA_MARKT.getScrapperType()))
            return ScrapperTypeEnum.MEDIA_MARKT.getScrapperType();

        throw new NoSuchScrapperEnumTypeException("Could not create Scrapper Type of: " + scrapperTypeFromUrl);
    }


}
