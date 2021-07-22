package com.mypricetracker.pricetracker.domain.product.impl;

import com.mypricetracker.pricetracker.domain.product.Product;
import com.mypricetracker.pricetracker.domain.product.ScrappingService;
import com.mypricetracker.pricetracker.scrapper.Scrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
public class ScrappingServiceImpl implements ScrappingService {

    private final ProductRepository productRepository;

    @Autowired
    public ScrappingServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void scrapDataFromUrlWithoutBorderPrice(Scrapper scrapper, String url) {
        productRepository.save(scrapper.scrapFromUrl(url));
    }

    @Override
    public void scrapDataFromUrlWithBorderPrice(Scrapper scrapper, String url, BigDecimal borderPrice) {
        Product product = scrapper.scrapFromUrl(url);
        product.setBorderPrice(borderPrice);
        productRepository.save(product);
    }


}
