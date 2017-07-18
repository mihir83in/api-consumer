package com.zp.apiconsumer.loadbalancer.rules;

import com.google.common.collect.Lists;
import com.zp.apiconsumer.client.CurrencyClient;
import com.zp.apiconsumer.client.currencylayer.CurrencyLayerClient;
import com.zp.apiconsumer.client.oxr.OxrClient;
import com.zp.apiconsumer.loadbalancer.LoadBalancerStats;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;


public class RandomLoadBalancerRuleTest {

    @InjectMocks
    private RandomLoadBalancerRule target;

    @Mock
    private CurrencyLayerClient currencyLayerClient;

    @Mock
    private OxrClient oxrClient;


    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        String currencyLayer = "currencyLayer";
        String oxr = "oxr";

        when(currencyLayerClient.getClientName()).thenReturn(currencyLayer);
        when(oxrClient.getClientName()).thenReturn(oxr);
    }


    @Test
    public void chooseInitially() throws Exception {

        LoadBalancerStats loadBalancerStats = new LoadBalancerStats();
        CurrencyClient client = target.choose(Lists.newArrayList(currencyLayerClient, oxrClient), loadBalancerStats);

        assertNotNull(client);
        assertEquals(client.getClientName(), loadBalancerStats.getLastClient().getClientName());
    }


    @Test
    public void chooseTwice() throws Exception {

        LoadBalancerStats loadBalancerStats = new LoadBalancerStats();
        ArrayList<CurrencyClient> currencyClients = Lists.newArrayList(currencyLayerClient, oxrClient);
        CurrencyClient client = target.choose(currencyClients, loadBalancerStats);
        CurrencyClient second = target.choose(currencyClients, loadBalancerStats);

        assertNotNull(client);
        assertEquals(second.getClientName(), loadBalancerStats.getLastClient().getClientName());
        assertNotEquals(client.getClientName(), second.getClientName());
    }

}
