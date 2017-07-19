package com.zp.apiconsumer.client.oxr;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Hosts configuration related to oxr feign client
 */
@Configuration
public class OxrClientConfiguration {

    private static final String API_KEY = "231fb4dc042f4578a033636b5dcdf26b";


    /**
     * Api key adding request interceptor
     *
     * @return api key adding interceptor
     */
    @Bean
    public RequestInterceptor apiKeyAddingInterceptor() {

        return requestTemplate -> requestTemplate.query("app_id", API_KEY);
    }
}
