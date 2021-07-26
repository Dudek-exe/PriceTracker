package com.mypricetracker.pricetracker.api.utils;

import com.mypricetracker.pricetracker.exception.UnreachableException;
import com.mypricetracker.pricetracker.scrapper.Scrapper;
import com.mypricetracker.pricetracker.scrapper.ScrapperContainer;
import com.mypricetracker.pricetracker.scrapper.ScrapperTypeEnum;
import org.springframework.stereotype.Component;

@Component
public class ScrapperChooser {

    private final ScrapperContainer scrapperContainer;

    public ScrapperChooser(ScrapperContainer scrapperContainer) {
        this.scrapperContainer = scrapperContainer;
    }

    public ScrapperTypeEnum extractEnumTypeFromUrl(String url) {
        url = url.replace("https://www.", "");
        String shopType = url.substring(0, 5);
        return ScrapperTypeEnum.from(shopType);
    }

    //TODO add more cases
    public Scrapper chooseScrapper(ScrapperTypeEnum scrapperTypeEnum) {
        switch (scrapperTypeEnum) {
            case XKOM:
                return scrapperContainer.getScrapperMap().get(scrapperTypeEnum);
        }
        throw new UnreachableException("Something went wrong");
    }


}
