package com.mypricetracker.pricetracker.api.xkom;

import com.mypricetracker.pricetracker.api.NewProductRequest;
import com.mypricetracker.pricetracker.domain.product.ScrappingService;
import com.mypricetracker.pricetracker.exception.NoSuchScrapperEnumTypeException;
import com.mypricetracker.pricetracker.scrapper.Scrapper;
import com.mypricetracker.pricetracker.scrapper.ScrapperContainer;
import com.mypricetracker.pricetracker.scrapper.ScrapperTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Slf4j
@RestController
@RequestMapping(value = "scrap")
public class ScrapperController {

    private final ScrappingService scrappingService;
    private final ScrapperContainer scrapperContainer;

    @Autowired
    public ScrapperController(ScrappingService service, ScrapperContainer scrapperContainer) {
        this.scrappingService = service;
        this.scrapperContainer = scrapperContainer;
    }

    @PostMapping
    public void scrapDataFromUrl(@RequestBody NewProductRequest newProductRequest, BigDecimal borderPrice) {
        log.info("Received request for scrappign data from url: " + newProductRequest.getUrl());
        ScrapperTypeEnum scrapperTypeEnum = extractEnumTypeFromUrl(newProductRequest.getUrl());
        Scrapper executableScrapper = chooseScrapper(scrapperTypeEnum);

        if(borderPrice ==null) {
            scrappingService.scrapDataFromUrlWithoutBorderPrice(executableScrapper, newProductRequest.getUrl());
        }else{

        }
    }

    private ScrapperTypeEnum extractEnumTypeFromUrl(String url) {
        url = url.replace("https://www.", "");
        String shopType = url.substring(0, 5);
        if (shopType.equals("x-kom")) {
            return ScrapperTypeEnum.XKOM;
        } else {
            throw new NoSuchScrapperEnumTypeException("Could not find shop of type: " + shopType);
        }
    }

    //TODO add more cases
    private Scrapper chooseScrapper(ScrapperTypeEnum scrapperTypeEnum) {
        switch (scrapperTypeEnum) {
            case XKOM:
                return scrapperContainer.getScrapperMap().get(scrapperTypeEnum);
        }
        //will never return null
        return null;
    }
}



