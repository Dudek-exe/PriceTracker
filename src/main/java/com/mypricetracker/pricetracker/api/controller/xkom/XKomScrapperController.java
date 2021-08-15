package com.mypricetracker.pricetracker.api.controller.xkom;

import com.mypricetracker.pricetracker.api.request.NewProductRequest;
import com.mypricetracker.pricetracker.api.response.ProductResponse;
import com.mypricetracker.pricetracker.api.response.SingleProductData;
import com.mypricetracker.pricetracker.api.utils.ScrapperChooser;
import com.mypricetracker.pricetracker.domain.product.ProductEntity;
import com.mypricetracker.pricetracker.domain.product.ScrappingService;
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
public class XKomScrapperController {

    private final ScrapperChooser scrapperChooser;
    private final ScrappingService scrappingService;
    private final FromProductEntityToSingleProductData fromProductEntityToSingleProductData;

    @GetMapping
    public ProductResponse getAllPricesOfProduct(@RequestParam String productName) {
        return new ProductResponse(scrappingService.getAllPricesForProduct(productName));
    }

    @PostMapping
    public ResponseEntity<SingleProductData> scrapDataFromUrl(@RequestBody NewProductRequest newProductRequest) {
        log.info("Received request for scrappign data from url: " + newProductRequest.getUrl());
        Scrapper executableScrapper = scrapperChooser.extractScrapperFromUrl(newProductRequest.getUrl());
        ProductEntity entityToBeSaved;

        if (newProductRequest.getBorderPrice() == null) {
            entityToBeSaved = scrappingService.scrapDataFromUrlWithoutBorderPrice(executableScrapper, newProductRequest.getUrl());
        } else {
            entityToBeSaved = scrappingService.scrapDataFromUrlWithBorderPrice(executableScrapper, newProductRequest.getUrl(), newProductRequest.getBorderPrice());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(fromProductEntityToSingleProductData.toSingleProductDataFromProductEntity(entityToBeSaved));
    }


}



