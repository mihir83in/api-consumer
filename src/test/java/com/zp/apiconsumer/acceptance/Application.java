package com.zp.apiconsumer.acceptance;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.feign.EnableFeignClients;


@SpringBootApplication(scanBasePackages = { "com.zp.apiconsumer.acceptance"})
@EnableConfigurationProperties
@EnableAutoConfiguration
@EnableFeignClients(basePackages = "com.zp.apiconsumer.acceptance")
public class Application {

    static {
        System.setProperty("sun.net.http.allowRestrictedHeaders", "true");
    }

}