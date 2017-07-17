package com.zp.apiconsumer.commons.validators;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;


public class BirthDateValidatorTest {

    private BirthDateValidator target;


    @Before
    public void setUp() throws Exception {

        target = new BirthDateValidator();
    }


    @Test
    public void isValid() throws Exception {

        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.YEAR, 2016);
        target.isValid(instance.getTime(), null);
    }


    @Test
    public void isValidNull() throws Exception {

        target.isValid(null, null);
    }


    @Test
    public void isValidNot() throws Exception {

        target.isValid(new Date(), null);
    }

}