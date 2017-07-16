package com.zp.apiconsumer.oxr.client;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OxrClientConfiguration {

    private static final String API_KEY = "231fb4dc042f4578a033636b5dcdf26b";

    @Bean
    public RequestInterceptor apiKeyAddingInterceptor() {
        return requestTemplate -> requestTemplate.query("app_id", API_KEY);
    }
}
