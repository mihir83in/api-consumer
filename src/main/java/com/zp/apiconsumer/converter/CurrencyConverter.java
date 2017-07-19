package com.zp.apiconsumer.converter;

import com.zp.apiconsumer.commons.Currency;

import javax.persistence.AttributeConverter;


/**
 * Jpa Converter for {@link Currency} Enum
 */
public class CurrencyConverter implements AttributeConverter<Currency, String> {

    @Override
    public String convertToDatabaseColumn(Currency attribute) {

        return attribute.getName();
    }


    @Override
    public Currency convertToEntityAttribute(String dbData) {

        return Currency.fromName(dbData);
    }
}
