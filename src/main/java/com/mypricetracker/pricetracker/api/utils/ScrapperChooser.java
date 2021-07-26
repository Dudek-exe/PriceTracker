package com.mypricetracker.pricetracker.api.utils;

import com.mypricetracker.pricetracker.exception.NoSuchScrapperEnumTypeException;
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

    public ScrapperTypeEnum extractEnumTypeFromUrl(String url, String urlConstant) {
        url = url.replace("https://www.", "");
        String shopType = url.substring(0, 5);
        if (shopType.contains(urlConstant)) {
            return ScrapperTypeEnum.XKOM;
        } else {
            throw new NoSuchScrapperEnumTypeException("Could not find shop of type: " + shopType);
        }
    }

    //TODO add more cases
    //TODO consider return null or leave UnreachableException
    public Scrapper chooseScrapper(ScrapperTypeEnum scrapperTypeEnum) {
        switch (scrapperTypeEnum) {
            case XKOM:
                return scrapperContainer.getScrapperMap().get(scrapperTypeEnum);
        }
        throw new UnreachableException("Something went wrong");
    }


}
