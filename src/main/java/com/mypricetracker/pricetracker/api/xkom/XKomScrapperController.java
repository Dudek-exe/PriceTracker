package com.mypricetracker.pricetracker.api.xkom;

import com.mypricetracker.pricetracker.api.request.NewProductRequest;
import com.mypricetracker.pricetracker.api.response.ProductResponse;
import com.mypricetracker.pricetracker.api.utils.ScrapperChooser;
import com.mypricetracker.pricetracker.domain.product.ScrappingService;
import com.mypricetracker.pricetracker.scrapper.Scrapper;
import com.mypricetracker.pricetracker.scrapper.ScrapperTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "scrap")
public class XKomScrapperController {

    private static final String ULR_CONSTANT = "x-kom";

    private final ScrapperChooser scrapperChooser;
    private final ScrappingService scrappingService;

    @Autowired
    public XKomScrapperController(ScrappingService service, ScrapperChooser scrapperChooser) {
        this.scrappingService = service;
        this.scrapperChooser = scrapperChooser;
    }

    @GetMapping
    public ProductResponse getAllPricesOfProduct(@RequestParam String productName) {
        return new ProductResponse(scrappingService.getAllPricesForProduct(productName));
    }

    @PostMapping
    public void scrapDataFromUrl(@RequestBody NewProductRequest newProductRequest) {
        log.info("Received request for scrappign data from url: " + newProductRequest.getUrl());
        ScrapperTypeEnum scrapperTypeEnum = scrapperChooser.extractEnumTypeFromUrl(newProductRequest.getUrl(), ULR_CONSTANT);
        Scrapper executableScrapper = scrapperChooser.chooseScrapper(scrapperTypeEnum);

        if (newProductRequest.getBorderPrice() == null) {
            scrappingService.scrapDataFromUrlWithoutBorderPrice(executableScrapper, newProductRequest.getUrl());
        } else {
            scrappingService.scrapDataFromUrlWithBorderPrice(executableScrapper, newProductRequest.getUrl(), newProductRequest.getBorderPrice());
        }
    }


}



