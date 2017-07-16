package com.zp.apiconsumer;

import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;
import com.zp.apiconsumer.currencylayer.client.CurrencyLayerClientConfiguration;
import com.zp.apiconsumer.oxr.client.OxrClientConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.retry.annotation.EnableRetry;

@ComponentScan(lazyInit = true, excludeFilters = @ComponentScan.Filter(value =
        {CurrencyLayerClientConfiguration.class, OxrClientConfiguration.class}, type = FilterType.ASSIGNABLE_TYPE))
@EnableFeignClients
@EnableRetry
@EnableCaching
@EnableMetrics
@SpringBootApplication
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
