package com.zp.apiconsumer.loadbalancer;

import com.zp.apiconsumer.client.CurrencyClient;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedHashMap;
import java.util.Map;


@Getter
@Setter
@ToString
public class LoadBalancerStats {

    private CurrencyClient lastClient;
    private Map<String, ServerStats> clientMap = new LinkedHashMap<>();
}
