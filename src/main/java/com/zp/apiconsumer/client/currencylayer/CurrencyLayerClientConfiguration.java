package com.zp.apiconsumer.client.currencylayer;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class CurrencyLayerClientConfiguration {

    private static final String API_KEY = "ba5e2f04e2df614788a5f611f656e34b";


    @Bean
    public RequestInterceptor apiKeyAddingInterceptor() {

        return requestTemplate -> requestTemplate.query("access_key", API_KEY);
    }
}
