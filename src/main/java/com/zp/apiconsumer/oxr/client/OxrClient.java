package com.zp.apiconsumer.oxr.client;

import com.codahale.metrics.annotation.Timed;
import com.zp.apiconsumer.currencylayer.client.CurrencyClient;
import com.zp.apiconsumer.commons.model.api.CurrencyList;
import com.zp.apiconsumer.commons.model.api.CurrencyRates;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "oxrClient", url = "https://openexchangerates.org/api", configuration = OxrClientConfiguration.class)
public interface OxrClient extends CurrencyClient {

    @GetMapping(value = "/currencies.json")
    @Override
    @Timed(name = "timer.oxr.currencies", absolute = true)
    public CurrencyList getSupportedCurrencies();

    @GetMapping(value = "/latest.json?symbols={symbols}")
    @Override
    @Timed(name = "timer.oxr.latest", absolute = true)
    CurrencyRates getLatestRates(@RequestParam(name = "symbols", required = false) String symbols);

    @GetMapping(value = "/historical/{date}.json")
    @Override
    @Timed(name = "timer.oxr.historical", absolute = true)
    CurrencyRates getHistoricalRates(@PathVariable("date") String date,
                                     @RequestParam(name = "symbols", required = false) String symbols);

    @Override
    default String getClientName() {
        return "oxrClient";
    }
}
