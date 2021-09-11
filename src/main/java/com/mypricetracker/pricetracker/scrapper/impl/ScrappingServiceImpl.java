package com.mypricetracker.pricetracker.scrapper.impl;

import com.mypricetracker.pricetracker.api.response.SingleProductData;
import com.mypricetracker.pricetracker.domain.product.ProductEntity;
import com.mypricetracker.pricetracker.scrapper.ScrappingService;
import com.mypricetracker.pricetracker.domain.product.impl.ProductRepository;
import com.mypricetracker.pricetracker.mapper.FromProductEntityToSingleProductData;
import com.mypricetracker.pricetracker.scrapper.Scrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ScrappingServiceImpl implements ScrappingService {

    private final ProductRepository productRepository;
    private final FromProductEntityToSingleProductData fromProductEntityToSingleProductData;

    @Autowired
    public ScrappingServiceImpl(ProductRepository productRepository, FromProductEntityToSingleProductData fromProductEntityToSingleProductDataImpl) {
        this.productRepository = productRepository;
        this.fromProductEntityToSingleProductData = fromProductEntityToSingleProductDataImpl;
    }

    @Override
    public ProductEntity scrapDataFromUrlWithoutBorderPrice(Scrapper scrapper, String url) {
        ProductEntity productEntity = scrapper.scrapFromUrl(url);
        log.info("Saving: " + productEntity);
        return productRepository.save(productEntity);
    }

    @Override
    public ProductEntity scrapDataFromUrlWithBorderPrice(Scrapper scrapper, String url, BigDecimal borderPrice) {
        ProductEntity productEntity = scrapper.scrapFromUrl(url);
        productEntity.setBorderPrice(borderPrice);
        log.info("Saving: " + productEntity);
        return productRepository.save(productEntity);
    }

    @Override
    public List<SingleProductData> getAllPricesForProduct(String name) {
        List<ProductEntity> productEntityList = productRepository.getAllByProductNameOrderByPriceDate(name);
        return productEntityList.stream().map(fromProductEntityToSingleProductData::toSingleProductDataFromProductEntity).collect(Collectors.toList());
    }

}
