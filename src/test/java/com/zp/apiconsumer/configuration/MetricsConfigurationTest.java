package com.zp.apiconsumer.configuration;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;


public class MetricsConfigurationTest {

    private MetricsConfiguration target;


    @Before
    public void setUp() throws Exception {

        target = new MetricsConfiguration();
    }


    @Test
    public void metricWriter() throws Exception {

        assertNotNull(target.metricWriter(null));
    }


    @Test
    public void metricsEndpointMetricReader() throws Exception {

        assertNotNull(target.metricsEndpointMetricReader(null));
    }

}