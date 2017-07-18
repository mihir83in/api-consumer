package com.zp.apiconsumer.client;

import com.zp.apiconsumer.commons.model.api.CurrencyList;
import com.zp.apiconsumer.commons.model.api.CurrencyRates;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class CurrencyLayerTemplate {

    private RestTemplate restTemplate;
    private static final String URL = "http://apilayer.net/api";
    private static final String API_KEY = "ba5e2f04e2df614788a5f611f656e34b";


    public CurrencyLayerTemplate(RestTemplateBuilder restTemplateBuilder) {

        restTemplate = restTemplateBuilder.build();
    }


    public CurrencyList getSupportedCurrencies() {

        String url = URL + "list" + "?" + "access_key=" + API_KEY;
        return restTemplate.getForEntity(URL + "list", CurrencyList.class).getBody();
    }


    public CurrencyRates getLatestRates(String currencies) {

        String url = URL + "live?currencies=" + currencies + "&" + "access_key=" + API_KEY;
        return restTemplate.getForEntity(url, CurrencyRates.class).getBody();
    }


    public CurrencyRates getHistoricalRates(String date, String currencies) {

        String url = URL + "historical?currencies=" + currencies + "&" + "date=" + date + "&" + "access_key=" + API_KEY;
        return restTemplate.getForEntity(URL + "list", CurrencyRates.class).getBody();
    }
}
