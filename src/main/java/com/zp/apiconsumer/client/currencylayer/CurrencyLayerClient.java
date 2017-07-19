package com.zp.apiconsumer.client.currencylayer;

import com.codahale.metrics.annotation.Timed;
import com.zp.apiconsumer.client.CurrencyClient;
import com.zp.apiconsumer.commons.model.api.CurrencyList;
import com.zp.apiconsumer.commons.model.api.CurrencyRates;
import com.zp.apiconsumer.constants.Constants;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Declarative rest client for Currency Layer website
 */
@FeignClient(name = Constants.CURRENCY_CLIENT_NAME, url = Constants.CURRENCY_LAYER_URL,
        configuration = CurrencyLayerClientConfiguration.class)
public interface CurrencyLayerClient extends CurrencyClient {

    /**
     * {@inheritDoc}
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @Override
    @Timed(name = "timer.currencylayer.currencies", absolute = true)
    public CurrencyList getSupportedCurrencies();

    /**
     * {@inheritDoc}
     */
    @RequestMapping(value = "/live?currencies={currencies}", method = RequestMethod.GET)
    @Override
    @Timed(name = "timer.currencylayer.latest", absolute = true)
    public CurrencyRates getLatestRates(@RequestParam(name = "currencies", required = false) String currencies);

    /**
     * {@inheritDoc}
     */
    @RequestMapping(value = "/historical?date={date}&currencies={currencies}", method = RequestMethod.GET)
    @Override
    @Timed(name = "timer.currencylayer.historical", absolute = true)
    CurrencyRates getHistoricalRates(@RequestParam("date") String date,
            @RequestParam(name = "currencies", required = false) String currencies);

    /**
     * {@inheritDoc}
     */
    @Override
    default String getClientName() {

        return Constants.CURRENCY_CLIENT_NAME;
    }
}
