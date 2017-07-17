package com.zp.apiconsumer.loadbalancer;

import com.zp.apiconsumer.client.CurrencyClient;
import com.zp.apiconsumer.loadbalancer.rules.LoadBalancerRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CurrencyClientLoadBalancer {

    private List<CurrencyClient> contracts;
    private LoadBalancerRule loadBalancerRule;
    private boolean singleServer;


    @Autowired
    public CurrencyClientLoadBalancer(List<CurrencyClient> clients, LoadBalancerRule loadBalancerRule) {

        this.contracts = clients;
        this.loadBalancerRule = loadBalancerRule;
        singleServer = contracts.size() == 1;
    }


    public CurrencyClient getClient(LoadBalancerStats loadBalancerStats) {

        if (singleServer) {
            return contracts.get(0);
        }

        return loadBalancerRule.choose(contracts, loadBalancerStats);
    }
}
