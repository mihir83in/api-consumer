package com.zp.apiconsumer;

import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;
import com.zp.apiconsumer.client.currencylayer.CurrencyLayerClientConfiguration;
import com.zp.apiconsumer.client.oxr.OxrClientConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.ribbon.FeignRibbonClientAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.retry.annotation.EnableRetry;


@ComponentScan(excludeFilters = @ComponentScan.Filter(value =
        { CurrencyLayerClientConfiguration.class, OxrClientConfiguration.class }, type = FilterType.ASSIGNABLE_TYPE))
@EnableRetry
@EnableCaching
@EnableMetrics
@SpringBootApplication
@EnableFeignClients
@EnableAutoConfiguration(exclude = FeignRibbonClientAutoConfiguration.class)
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }
}
