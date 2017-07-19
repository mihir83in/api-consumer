package com.zp.apiconsumer.controller;

import com.zp.apiconsumer.commons.model.api.CurrencyList;
import com.zp.apiconsumer.commons.model.api.CurrencyRates;
import com.zp.apiconsumer.constants.Constants;
import com.zp.apiconsumer.services.CurrencyConverterService;
import org.apache.commons.lang3.time.FastDateFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


@RestController
public class ManagementApiController {

    private CurrencyConverterService currencyConverterService;
    private FastDateFormat dateFormat = FastDateFormat.getInstance(Constants.DATE_PATTERN);


    public ManagementApiController(CurrencyConverterService currencyConverterService) {

        this.currencyConverterService = currencyConverterService;
    }


    @RequestMapping(value = "/management/currencies", method = RequestMethod.GET)
    public CurrencyList supportedCur() {

        return currencyConverterService.getSupportedCurrencies();
    }


    @RequestMapping(value = "/management/latest", method = RequestMethod.GET)
    public CurrencyRates latest() {

        return currencyConverterService.getLatestRates();
    }


    @RequestMapping(value = "/management/historical", method = RequestMethod.GET)
    public CurrencyRates historical(@RequestParam(value = "date", required = false)
    @DateTimeFormat(pattern = Constants.DATE_PATTERN) Date date) {

        return currencyConverterService.getHistoricalRates(dateFormat.format(date));
    }

}
