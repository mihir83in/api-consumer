/*
package com.zp.apiconsumer.services;

import com.zp.apiconsumer.client.oxr.OxrClient;
import com.zp.apiconsumer.commons.Currency;
import com.zp.apiconsumer.commons.model.api.CurrencyRates;
import com.zp.apiconsumer.commons.model.api.ErrorResponse;
import com.zp.apiconsumer.exception.ApiConsumerException;
import com.zp.apiconsumer.loadbalancer.CurrencyClientLoadBalancer;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.actuate.metrics.dropwizard.DropwizardMetricServices;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class CurrencyConverterServiceTest {

    @InjectMocks
    private CurrencyConverterService target;

    @Mock
    private CurrencyClientLoadBalancer currencyClientLoadBalancer;

    @Mock
    private DropwizardMetricServices metricServices;

    @Mock
    private OxrClient oxrClient;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();


    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        when(currencyClientLoadBalancer.getClient(anyObject())).thenReturn(oxrClient);
    }


    @Test
    public void getSupportedCurrencies() throws Exception {

        target.getSupportedCurrencies();

        verify(oxrClient).getSupportedCurrencies();
    }


    @Test
    public void getHistoricalRates() throws Exception {

        String date = "2017-05-05";
        String stringify = Currency.stringify();

        when(oxrClient.getHistoricalRates(date, stringify)).thenReturn(new CurrencyRates());
        target.getHistoricalRates(date);
        verify(oxrClient).getHistoricalRates(date, stringify);
    }


    @Test
    public void getHistoricalRates1() throws Exception {

        String date = "2017-05-05";
        String stringify = Currency.AUD.getName() + "," + Currency.USD.getName();

        when(oxrClient.getHistoricalRates(date, stringify)).thenReturn(new CurrencyRates());
        target.getHistoricalRates(Currency.AUD, Currency.USD, date);
        verify(oxrClient).getHistoricalRates(date, stringify);
    }


    @Test
    public void getLatestRates() throws Exception {

        String stringify = Currency.stringify();

        when(oxrClient.getLatestRates(stringify)).thenReturn(new CurrencyRates());
        target.getLatestRates();
        verify(oxrClient).getLatestRates(stringify);
    }


    @Test
    public void getLatestRates1() throws Exception {

        String stringify = Currency.AUD.getName() + "," + Currency.USD.getName();

        when(oxrClient.getLatestRates(stringify)).thenReturn(new CurrencyRates());
        target.getLatestRates(Currency.AUD, Currency.USD);
        verify(oxrClient).getLatestRates(stringify);
    }


    @Test
    public void getLatestRatesThrowsUp() throws Exception {

        ErrorResponse errorResponse = new ErrorResponse(404, "not found");

        expectedException.expect(ApiConsumerException.class);
        expectedException.expectMessage(errorResponse.toString());

        String stringify = Currency.stringify();

        CurrencyRates currencyRates = new CurrencyRates();
        currencyRates.setErrorResponse(errorResponse);

        when(oxrClient.getLatestRates(stringify)).thenReturn(currencyRates);
        target.getLatestRates();
    }
}*/
