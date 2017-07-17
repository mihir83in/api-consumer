package com.zp.apiconsumer.converter;

import com.zp.apiconsumer.commons.Currency;
import com.zp.apiconsumer.commons.model.web.CurrencyConversionForm;
import com.zp.apiconsumer.commons.model.web.CurrencyQuery;
import com.zp.apiconsumer.commons.model.web.CurrencyUserRegistrationForm;
import com.zp.apiconsumer.persistence.model.CurrencyConvertQuery;
import com.zp.apiconsumer.persistence.model.CurrencyUser;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class ModelConverterTest {

    private ModelConverter target;


    @Before
    public void setUp() {

        target = new ModelConverter();
    }


    @Test
    public void toCurrencyUser() throws Exception {

        String username = "foobar";

        CurrencyUserRegistrationForm form = new CurrencyUserRegistrationForm();
        form.setUsername(username);

        CurrencyUser currencyUser = target.toCurrencyUser(form);

        assertNotNull(currencyUser);
        assertEquals(username, currencyUser.getUsername());
    }


    @Test
    public void toCurrencyConvertQuery() throws Exception {

        CurrencyConversionForm form = new CurrencyConversionForm();
        form.setFrom(Currency.AUD);

        CurrencyConvertQuery query = target.toCurrencyConvertQuery(form);

        assertNotNull(query);
        assertEquals(Currency.AUD, query.getFromCurrency());
    }


    @Test
    public void toCurrencyQuery() throws Exception {

        String username = "foobar";

        CurrencyConvertQuery query = new CurrencyConvertQuery();
        query.setFromCurrency(Currency.AUD);

        CurrencyQuery currencyQuery = target.toCurrencyQuery(query);

        assertNotNull(currencyQuery);
        assertEquals(Currency.AUD, query.getFromCurrency());
    }

}