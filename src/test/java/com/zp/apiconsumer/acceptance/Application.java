package com.zp.apiconsumer.acceptance;

import com.zp.apiconsumer.client.currencylayer.CurrencyLayerClientConfiguration;
import com.zp.apiconsumer.client.oxr.OxrClientConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;


@ComponentScan(excludeFilters = @ComponentScan.Filter(value =
        { CurrencyLayerClientConfiguration.class, OxrClientConfiguration.class }, type = FilterType.ASSIGNABLE_TYPE))
@SpringBootApplication(scanBasePackages = { "com.zp.apiconsumer.acceptance"})
@EnableConfigurationProperties
@EnableAutoConfiguration
@EnableFeignClients(basePackages = "com.zp.apiconsumer.acceptance")
public class Application {

    static {
        System.setProperty("sun.net.http.allowRestrictedHeaders", "true");
    }
}