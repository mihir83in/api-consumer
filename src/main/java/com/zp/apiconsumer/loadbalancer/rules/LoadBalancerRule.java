package com.zp.apiconsumer.loadbalancer.rules;

import com.zp.apiconsumer.client.CurrencyClient;
import com.zp.apiconsumer.loadbalancer.LoadBalancerStats;

import java.util.List;


/**
 * Contract for defining how load balancer would choose a server
 */
public interface LoadBalancerRule {

    /**
     * Chooses a client based on list of clients and per server state.
     *
     * @param converters        list of clients
     * @param loadBalancerStats Stats that help load balancer to choose
     * @return {@link CurrencyClient}
     */
    CurrencyClient choose(List<CurrencyClient> converters, LoadBalancerStats loadBalancerStats);
}
