package com.zp.apiconsumer.loadbalancer.rules;

import com.zp.apiconsumer.client.CurrencyClient;
import com.zp.apiconsumer.loadbalancer.LoadBalancerStats;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;


/**
 * An implementation that randomizes client selection
 */
@Component
public class RandomLoadBalancerRule implements LoadBalancerRule {

    /**
     * Chooses a client randomly making sure it wasn't the previously selected one
     *
     * @param converters        list of clients
     * @param loadBalancerStats Stats that help load balancer to choose
     * @return {@link CurrencyClient}
     */
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
