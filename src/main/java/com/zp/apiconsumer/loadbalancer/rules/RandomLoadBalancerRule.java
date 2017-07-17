package com.zp.apiconsumer.loadbalancer.rules;

import com.zp.apiconsumer.client.CurrencyClient;
import com.zp.apiconsumer.loadbalancer.LoadBalancerStats;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;


@Component
public class RandomLoadBalancerRule implements LoadBalancerRule {

    @Override
    public CurrencyClient choose(List<CurrencyClient> converters, LoadBalancerStats loadBalancerStats) {

        CurrencyClient currencyClient = converters.get(ThreadLocalRandom.current().nextInt(0, converters.size()));

        if (!Optional.ofNullable(loadBalancerStats.getLastClient()).isPresent()) {

            loadBalancerStats.setLastClient(currencyClient);
            return currencyClient;
        }

        if (currencyClient.getClientName().equals(loadBalancerStats.getLastClient().getClientName())) {

            currencyClient = choose(converters, loadBalancerStats);
            loadBalancerStats.setLastClient(currencyClient);

            return currencyClient;
        }

        loadBalancerStats.setLastClient(currencyClient);

        return currencyClient;
    }
}
