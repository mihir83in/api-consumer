/*
package com.zp.apiconsumer.client.currencylayer;

import com.codahale.metrics.annotation.Timed;
import com.zp.apiconsumer.client.CurrencyClient;
import com.zp.apiconsumer.commons.model.api.CurrencyList;
import com.zp.apiconsumer.commons.model.api.CurrencyRates;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "currencyLayerClient", url = "http://apilayer.net/api",
        configuration = CurrencyLayerClientConfiguration.class)
public interface CurrencyLayerClient extends CurrencyClient {

    @GetMapping(value = "/list")
    @Override
    @Timed(name = "timer.currencylayer.currencies", absolute = true)
    public CurrencyList getSupportedCurrencies();

    @GetMapping(value = "/live?currencies={currencies}")
    @Override
    @Timed(name = "timer.currencylayer.latest", absolute = true)
    public CurrencyRates getLatestRates(@RequestParam(name = "currencies", required = false) String currencies);

    @GetMapping(value = "/historical?date={date}&currencies={currencies}")
    @Override
    @Timed(name = "timer.currencylayer.historical", absolute = true)
    CurrencyRates getHistoricalRates(@RequestParam("date") String date,
            @RequestParam(name = "currencies", required = false) String currencies);

    @Override
    default String getClientName() {

        return "currencyLayerClient";
    }
}
*/
