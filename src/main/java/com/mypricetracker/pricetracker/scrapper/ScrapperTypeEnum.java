package com.mypricetracker.pricetracker.scrapper;

import com.mypricetracker.pricetracker.exception.NoSuchScrapperEnumTypeException;
import lombok.Getter;

@Getter
public enum ScrapperTypeEnum {

    XKOM("XKOM"),
    MEDIA_MARKT("MEDIAMARKT"),
    JULA("JULA"),
    BIKESTER("BIKESTER");

    String scrapperType;

    ScrapperTypeEnum(String scrapperType) {
        this.scrapperType = scrapperType;
    }

    public static ScrapperTypeEnum from(String inputType) {
        for (ScrapperTypeEnum enumType : ScrapperTypeEnum.values()) {
            if (enumType.scrapperType.equalsIgnoreCase(inputType)) {
                return enumType;
            }
        }
        throw new NoSuchScrapperEnumTypeException("Could not create Scrapper Type of: " + inputType);
    }

}
