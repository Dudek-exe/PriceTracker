package com.mypricetracker.pricetracker.api.controller;

import com.mypricetracker.pricetracker.api.request.ProductSubscriptionRequest;
import com.mypricetracker.pricetracker.api.response.ProductResponse;
import com.mypricetracker.pricetracker.api.response.SingleProductData;
import com.mypricetracker.pricetracker.api.utils.ScrapperChooser;
import com.mypricetracker.pricetracker.domain.product.ProductEntity;
import com.mypricetracker.pricetracker.scrapper.ScrappingService;
import com.mypricetracker.pricetracker.mapper.FromProductEntityToSingleProductData;
import com.mypricetracker.pricetracker.scrapper.Scrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "scrap")
public class ApiController {

    private final ScrapperChooser scrapperChooser;
    private final ScrappingService scrappingService;
    private final FromProductEntityToSingleProductData fromProductEntityToSingleProductData;

    @GetMapping
    public ProductResponse getAllProductsByName(@RequestParam String productName) {
        return new ProductResponse(scrappingService.getAllPricesForProduct(productName));
    }

    @PostMapping
    public ResponseEntity<SingleProductData> scrapDataFromUrl(@RequestBody ProductSubscriptionRequest productSubscriptionRequest) {
        log.info("Received request for scrappign data from url: " + productSubscriptionRequest.getUrl());
        Scrapper executableScrapper = scrapperChooser.extractScrapperFromUrl(productSubscriptionRequest.getUrl());
        ProductEntity entityToBeSaved;

        if (productSubscriptionRequest.getBorderPrice() == null) {
            entityToBeSaved = scrappingService.scrapDataFromUrlWithoutBorderPrice(executableScrapper, productSubscriptionRequest.getUrl());
        } else {
            entityToBeSaved = scrappingService.scrapDataFromUrlWithBorderPrice(executableScrapper, productSubscriptionRequest.getUrl(), productSubscriptionRequest.getBorderPrice());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(fromProductEntityToSingleProductData.toSingleProductDataFromProductEntity(entityToBeSaved));
    }


}



