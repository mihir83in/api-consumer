package com.zp.apiconsumer.commons;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ArrayUtils;


@AllArgsConstructor
@Slf4j
public enum Currency {

    AUD("AUD", "Australian Dollar"), EUR("EUR", "Euro"), GBP("GBP", "British Pound Sterling"),
    HUF("HUF", "Hungarian Forint"), JPY("JPY", "Japanese Yen"), NZD("NZD", "New Zealand Dollar"),
    USD("USD", "United States Dollar");

    @Getter
    private String name;

    @Getter
    private String displayName;


    public static String stringify() {

        return ArrayUtils.toString(Currency.values()).replace("{", "").replace("}", "");
    }


    public static Currency fromName(String name) {

        try {
            return Currency.valueOf(name);
        }
        catch (Exception e) {
            return null;
        }
    }
}
