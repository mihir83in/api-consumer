package com.zp.apiconsumer.configuration;

import org.springframework.boot.actuate.autoconfigure.ExportMetricWriter;
import org.springframework.boot.actuate.endpoint.MetricsEndpoint;
import org.springframework.boot.actuate.endpoint.MetricsEndpointMetricReader;
import org.springframework.boot.actuate.metrics.jmx.JmxMetricWriter;
import org.springframework.boot.actuate.metrics.writer.MetricWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jmx.export.MBeanExporter;
import org.springframework.web.client.RestTemplate;


@Configuration
public class MetricsConfiguration {

    @Bean
    @ExportMetricWriter
    MetricWriter metricWriter(MBeanExporter exporter) {

        return new JmxMetricWriter(exporter);
    }


    @Bean
    public MetricsEndpointMetricReader metricsEndpointMetricReader(MetricsEndpoint metricsEndpoint) {

        return new MetricsEndpointMetricReader(metricsEndpoint);
    }
}
