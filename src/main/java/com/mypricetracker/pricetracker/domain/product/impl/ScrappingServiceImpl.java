package com.mypricetracker.pricetracker.domain.product.impl;

import com.mypricetracker.pricetracker.api.response.SingleProductData;
import com.mypricetracker.pricetracker.domain.product.ProductEntity;
import com.mypricetracker.pricetracker.domain.product.ScrappingService;
import com.mypricetracker.pricetracker.mapper.FromProductEntityToProductResponse;
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
    private final FromProductEntityToProductResponse fromProductEntityToProductResponse;

    @Autowired
    public ScrappingServiceImpl(ProductRepository productRepository, FromProductEntityToProductResponse fromProductEntityToProductResponseImpl) {
        this.productRepository = productRepository;
        this.fromProductEntityToProductResponse = fromProductEntityToProductResponseImpl;
    }

    @Override
    public void scrapDataFromUrlWithoutBorderPrice(Scrapper scrapper, String url) {
        productRepository.save(scrapper.scrapFromUrl(url));
    }

    @Override
    public void scrapDataFromUrlWithBorderPrice(Scrapper scrapper, String url, BigDecimal borderPrice) {
        ProductEntity product = scrapper.scrapFromUrl(url);
        product.setBorderPrice(borderPrice);
        productRepository.save(product);
    }

    @Override
    public List<SingleProductData> getAllPricesForProduct(String name) {
        List<ProductEntity> productEntityList = productRepository.getAllByProductNameOrderByPriceDate(name);
        return productEntityList.stream().map(fromProductEntityToProductResponse::toSingleProductDataFromProductEntity).collect(Collectors.toList());
    }

}
