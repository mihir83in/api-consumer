package com.zp.apiconsumer.loadbalancer;

import com.zp.apiconsumer.currencylayer.client.CurrencyClient;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@Setter
public class LoadBalancerStats {

    private CurrencyClient lastClient;
    private Map<String, ServerStats> clientMap = new LinkedHashMap<>();
}
