package com.zp.apiconsumer.commons.validators;

import org.apache.commons.lang.LocaleUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Locale;

import static org.junit.Assert.*;

public class CountryValidatorTest {

    private CountryValidator target;

    @Before
    public void setUp() throws Exception {
        target = new CountryValidator();
    }

    @Test
    public void isValid() throws Exception {

        //assertTrue(target.isValid("England", null));
        System.out.println(Calendar.getInstance().get(Calendar.YEAR));
    }

}