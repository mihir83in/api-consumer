package com.zp.apiconsumer.client.oxr;

import com.codahale.metrics.annotation.Timed;
import com.zp.apiconsumer.client.CurrencyClient;
import com.zp.apiconsumer.commons.model.api.CurrencyList;
import com.zp.apiconsumer.commons.model.api.CurrencyRates;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "oxrClient", url = "https://openexchangerates.org/api", configuration = OxrClientConfiguration.class)
public interface OxrClient extends CurrencyClient {

    @RequestMapping(value = "/currencies.json", method = RequestMethod.GET)
    @Override
    @Timed(name = "timer.oxr.currencies", absolute = true)
    CurrencyList getSupportedCurrencies();

    @RequestMapping(value = "/latest.json?symbols={symbols}", method = RequestMethod.GET)
    @Override
    @Timed(name = "timer.oxr.latest", absolute = true)
    CurrencyRates getLatestRates(@RequestParam(name = "symbols", required = false) String symbols);

    @RequestMapping(value = "/historical/{date}.json", method = RequestMethod.GET)
    @Override
    @Timed(name = "timer.oxr.historical", absolute = true)
    CurrencyRates getHistoricalRates(@PathVariable("date") String date,
            @RequestParam(name = "symbols", required = false) String symbols);

    @Override
    default String getClientName() {

        return "oxrClient";
    }
}
