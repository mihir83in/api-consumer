package com.zp.apiconsumer.client.oxr;

import com.codahale.metrics.annotation.Timed;
import com.zp.apiconsumer.client.CurrencyClient;
import com.zp.apiconsumer.commons.model.api.CurrencyList;
import com.zp.apiconsumer.commons.model.api.CurrencyRates;
import com.zp.apiconsumer.constants.Constants;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Declarative rest client for open exchange rates website
 */
@FeignClient(name = Constants.OXR_CLIENT_NAME, url = Constants.OXR_URL, configuration = OxrClientConfiguration.class)
public interface OxrClient extends CurrencyClient {

    /**
     * {@inheritDoc}
     */
    @RequestMapping(value = "/currencies.json", method = RequestMethod.GET)
    @Override
    @Timed(name = "timer.oxr.currencies", absolute = true)
    CurrencyList getSupportedCurrencies();

    /**
     * {@inheritDoc}
     */
    @RequestMapping(value = "/latest.json?symbols={symbols}", method = RequestMethod.GET)
    @Override
    @Timed(name = "timer.oxr.latest", absolute = true)
    CurrencyRates getLatestRates(@RequestParam(name = "symbols", required = false) String symbols);

    /**
     * {@inheritDoc}
     */
    @RequestMapping(value = "/historical/{date}.json", method = RequestMethod.GET)
    @Override
    @Timed(name = "timer.oxr.historical", absolute = true)
    CurrencyRates getHistoricalRates(@PathVariable("date") String date,
            @RequestParam(name = "symbols", required = false) String symbols);

    /**
     * {@inheritDoc}
     */
    @Override
    default String getClientName() {

        return Constants.OXR_CLIENT_NAME;
    }
}
