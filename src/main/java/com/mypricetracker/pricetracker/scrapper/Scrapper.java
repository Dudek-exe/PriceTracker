package com.mypricetracker.pricetracker.scrapper;

import com.mypricetracker.pricetracker.domain.product.ProductEntity;
import com.mypricetracker.pricetracker.exception.ScrappingFieldException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public abstract class Scrapper {

    private static ScrapperTypeEnum scrapperTypeEnum;

    public ScrapperTypeEnum getScrapperTypeEnum() {
        return scrapperTypeEnum;
    }

   public abstract ProductEntity scrapFromUrl(String url);

    protected String scrapStringField(Document document, String field) {
        Element tempElement = document.select(field).first();
        if (tempElement != null) {
            return tempElement.select(field).text();
        } else {
            throw new ScrappingFieldException("Could not scrap field: " + field);
        }
    }

    protected BigDecimal scrapPriceField(Document document, String field) {
        Element tempElement = document.select(field).first();
        if (tempElement != null) {
            String price = tempElement.text();
            price = convertOriginalPrice(price);
            return new BigDecimal(price);
        } else {
            throw new ScrappingFieldException("Could not scrap field: " + field);
        }
    }

    protected String convertOriginalPrice(String price) {
        price = price.replaceAll("[^\\d,.]", "").replace(",",".");
        return price.replaceAll("[^\\d,.]", "");
    }

    protected ProductEntity createProduct(String name, BigDecimal price, OffsetDateTime priceTime, ScrapperTypeEnum scrapperTypeEnum){
        ProductEntity productEntity = new ProductEntity();

        productEntity.setProductName(name);
        productEntity.setProductPrice(price);
        productEntity.setPriceDate(priceTime);
        productEntity.setShopType(scrapperTypeEnum);

        return productEntity;
    }

}
