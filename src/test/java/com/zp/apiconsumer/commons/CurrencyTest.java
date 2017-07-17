package com.zp.apiconsumer.commons;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;


public class CurrencyTest {

    @Test
    public void stringify() throws Exception {

        String stringify = Currency.stringify();

        assertFalse(stringify.contains("{"));
        assertFalse(stringify.contains("}"));
    }


    @Test
    public void fromName() throws Exception {

        assertEquals(Currency.AUD, Currency.fromName(Currency.AUD.getName()));
    }


    @Test
    public void fromNameNull() throws Exception {

        Currency newcur = Currency.fromName("NEWCUR");
        assertNull(newcur);
    }

}