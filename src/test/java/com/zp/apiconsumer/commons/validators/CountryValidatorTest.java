package com.zp.apiconsumer.commons.validators;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class CountryValidatorTest {

    private CountryValidator target;


    @Before
    public void setUp() throws Exception {

        target = new CountryValidator();
    }


    @Test
    public void isValid() throws Exception {

        assertTrue(target.isValid("United Kingdom", null));
    }


    @Test
    public void isValidNot() throws Exception {

        assertFalse(target.isValid("England", null));
    }

}