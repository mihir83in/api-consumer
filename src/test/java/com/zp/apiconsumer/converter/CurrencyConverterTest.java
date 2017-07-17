package com.zp.apiconsumer.converter;

import com.zp.apiconsumer.commons.Currency;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class CurrencyConverterTest {

    private CurrencyConverter target;


    @Before
    public void setUp() throws Exception {

        target = new CurrencyConverter();
    }


    @Test
    public void convertToDatabaseColumn() throws Exception {

        assertEquals(Currency.AUD.getName(), target.convertToDatabaseColumn(Currency.AUD));
    }


    @Test
    public void convertToEntityAttribute() throws Exception {

        assertEquals(Currency.AUD, target.convertToEntityAttribute(Currency.AUD.getName()));
    }

}