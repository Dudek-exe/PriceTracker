package com.mypricetracker.pricetracker.api.utils;

import com.mypricetracker.pricetracker.exception.UnreachableException;
import com.mypricetracker.pricetracker.scrapper.Scrapper;
import com.mypricetracker.pricetracker.scrapper.ScrapperContainer;
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
        url = url.replace("https://www.", "").replace("-", "");
        String scrapperTypeFromUrl = url.substring(0, 10).toUpperCase(Locale.ROOT);
        return scrapperContainer.getScrapperMap().get(ScrapperTypeEnum.from(scrapperTypeFromUrl));
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
