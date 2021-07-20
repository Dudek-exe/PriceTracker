package com.mypricetracker.pricetracker.domain.product.impl;

import com.mypricetracker.pricetracker.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
