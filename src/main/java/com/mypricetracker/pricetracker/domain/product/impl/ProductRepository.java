package com.mypricetracker.pricetracker.domain.product.impl;

import com.mypricetracker.pricetracker.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {


}
