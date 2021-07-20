package com.mypricetracker.pricetracker.domain.product.impl;

import com.mypricetracker.pricetracker.domain.product.Product;
import com.mypricetracker.pricetracker.domain.product.ScrappingService;
import com.mypricetracker.pricetracker.scrapper.Scrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScrappingServiceImpl implements ScrappingService {

    private final ProductRepository productRepository;

    @Autowired
    public ScrappingServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public Product scrapDataFromUrl(Scrapper scrapper, String url) {
        scrapper.scrapFromUrl(url);
        return null;
    }


}
