package com.mypricetracker.pricetracker.mapper;

import com.mypricetracker.pricetracker.api.response.SingleProductData;
import com.mypricetracker.pricetracker.domain.product.ProductEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public abstract class FromProductEntityToSingleProductData {
    //For further implementation of utils methods
    interface Helper {
        static String toDoubleFromJSON(BigDecimal decimalFromEnTity) {
            return String.format("%.2f", decimalFromEnTity).replace(",",".");
        }
    }

    @Mapping(target = "productName", source = "productEntity.productName")
    @Mapping(target = "productPrice", expression = "java(Helper.toDoubleFromJSON(productEntity.getProductPrice()))")
    @Mapping(target = "priceDate", source = "productEntity.priceDate")
    @Mapping(target = "borderPrice", source = "productEntity.borderPrice")
    @Mapping(target = "shopType", source = "productEntity.shopType")
    public abstract SingleProductData toSingleProductDataFromProductEntity(ProductEntity productEntity);

}
