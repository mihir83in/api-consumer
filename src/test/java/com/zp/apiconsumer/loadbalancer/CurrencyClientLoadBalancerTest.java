package com.zp.apiconsumer.loadbalancer;

import com.google.common.collect.Lists;
import com.zp.apiconsumer.client.CurrencyClient;
import com.zp.apiconsumer.client.currencylayer.CurrencyLayerClient;
import com.zp.apiconsumer.client.oxr.OxrClient;
import com.zp.apiconsumer.loadbalancer.rules.LoadBalancerRule;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class CurrencyClientLoadBalancerTest {

    private CurrencyClientLoadBalancer target;

    @Mock
    private LoadBalancerRule loadBalancerRule;

    @Mock
    private CurrencyLayerClient currencyLayerClient;

    @Mock
    private OxrClient oxrClient;


    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);

        String currencyLayer = "currencyLayer";
        String oxr = "oxr";

        when(currencyLayerClient.getClientName()).thenReturn(currencyLayer);
        when(oxrClient.getClientName()).thenReturn(oxr);

        target = new CurrencyClientLoadBalancer(Lists.newArrayList(currencyLayerClient, oxrClient), loadBalancerRule);
    }


    @Test
    public void getClientOnlyOne() throws Exception {

        LoadBalancerStats loadBalancerStats = new LoadBalancerStats();
        target = new CurrencyClientLoadBalancer(Lists.newArrayList(currencyLayerClient), loadBalancerRule);

        CurrencyClient client = target.getClient(loadBalancerStats);

        verify(loadBalancerRule, never()).choose(anyList(), anyObject());
        assertEquals(client.getClientName(), currencyLayerClient.getClientName());
    }


    @Test
    public void getClient() throws Exception {

        LoadBalancerStats loadBalancerStats = new LoadBalancerStats();
        target = new CurrencyClientLoadBalancer(Lists.newArrayList(currencyLayerClient, oxrClient), loadBalancerRule);

        target.getClient(loadBalancerStats);
        verify(loadBalancerRule).choose(anyList(), eq(loadBalancerStats));
    }

}
