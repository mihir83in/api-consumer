package com.zp.apiconsumer.configuration;

import org.springframework.boot.actuate.autoconfigure.ExportMetricWriter;
import org.springframework.boot.actuate.endpoint.MetricsEndpoint;
import org.springframework.boot.actuate.endpoint.MetricsEndpointMetricReader;
import org.springframework.boot.actuate.metrics.jmx.JmxMetricWriter;
import org.springframework.boot.actuate.metrics.writer.MetricWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jmx.export.MBeanExporter;


@Configuration
public class MetricsConfiguration {

    /**
     * Needed to export all metrics to JMX
     *
     * @param exporter exporter
     * @return Writer
     */
    @Bean
    @ExportMetricWriter
    MetricWriter metricWriter(MBeanExporter exporter) {

        return new JmxMetricWriter(exporter);
    }


    /**
     * Required to export all metrics to jmx, this reads metrics from /metrics
     *
     * @param metricsEndpoint autowired metricsEndpoint
     * @return Reader
     */
    @Bean
    public MetricsEndpointMetricReader metricsEndpointMetricReader(MetricsEndpoint metricsEndpoint) {

        return new MetricsEndpointMetricReader(metricsEndpoint);
    }
}
