package com.zp.apiconsumer.client;

import com.zp.apiconsumer.commons.model.api.CurrencyList;
import com.zp.apiconsumer.commons.model.api.CurrencyRates;


/**
 * Contract for currency clients.
 */
public interface CurrencyClient {

    /**
     * Returns supported currencies.
     *
     * @return {@link CurrencyList list} of Supported Currencies
     */
    CurrencyList getSupportedCurrencies();

    /**
     * Returns latest rates of currencies.
     *
     * @param symbols Currency symbols whose latest rates will be returned.
     * @return {@link CurrencyRates rates} of Currencies
     */
    CurrencyRates getLatestRates(String symbols);

    /**
     * Returns historical rates of currencies.
     *
     * @param symbols Currency symbols whose rates will be returned.
     * @param date    date in history for rates reference
     * @return {@link CurrencyRates rates} of Currencies
     */
    CurrencyRates getHistoricalRates(String date, String symbols);

    /**
     * Client Identifier String.
     *
     * @return Name of the client.
     */
    default String getClientName() {

        return this.getClass().getSimpleName();
    }
}
