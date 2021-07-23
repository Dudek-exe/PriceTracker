package com.mypricetracker.pricetracker.domain.product.impl;

import com.mypricetracker.pricetracker.domain.product.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> getAllByProductNameOrderByPriceDate(String name);

}
