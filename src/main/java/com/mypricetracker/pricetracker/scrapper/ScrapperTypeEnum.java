package com.mypricetracker.pricetracker.scrapper;

import com.mypricetracker.pricetracker.exception.NoSuchScrapperEnumTypeException;
import lombok.Getter;

@Getter
public enum ScrapperTypeEnum {

    XKOM("XKOM");

    String scrapperType;

    ScrapperTypeEnum(String scrapperType) {
        this.scrapperType = scrapperType;
    }

    public ScrapperTypeEnum from(String inputType) {
        for (ScrapperTypeEnum enumType : ScrapperTypeEnum.values()) {
            if (enumType.scrapperType.equals(inputType)) {
                return enumType;
            }
        }
        throw new NoSuchScrapperEnumTypeException("Could not create Scrapper Type of: " + inputType);
    }

}
