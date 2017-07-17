package com.zp.apiconsumer.client;

import com.zp.apiconsumer.commons.model.api.CurrencyList;
import com.zp.apiconsumer.commons.model.api.CurrencyRates;


public interface CurrencyClient {

    CurrencyList getSupportedCurrencies();

    CurrencyRates getLatestRates(String symbols);

    CurrencyRates getHistoricalRates(String date, String symbols);

    default String getClientName() {

        return this.getClass().getSimpleName();
    }
}
