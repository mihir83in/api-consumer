package com.zp.apiconsumer.loadbalancer.rules;

import com.zp.apiconsumer.client.CurrencyClient;
import com.zp.apiconsumer.loadbalancer.LoadBalancerStats;

import java.util.List;


public interface LoadBalancerRule {

    CurrencyClient choose(List<CurrencyClient> converters, LoadBalancerStats loadBalancerStats);
}
