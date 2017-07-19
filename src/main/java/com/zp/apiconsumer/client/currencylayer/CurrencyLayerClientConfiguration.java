package com.zp.apiconsumer.client.currencylayer;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Hosts configuration related to currency layer feign client
 */
@Configuration
public class CurrencyLayerClientConfiguration {

    private static final String API_KEY = "ba5e2f04e2df614788a5f611f656e34b";


    /**
     * Api key adding request interceptor
     *
     * @return api key adding interceptor
     */
    @Bean
    public RequestInterceptor apiKeyAddingInterceptor() {

        return requestTemplate -> requestTemplate.query("access_key", API_KEY);
    }
}
