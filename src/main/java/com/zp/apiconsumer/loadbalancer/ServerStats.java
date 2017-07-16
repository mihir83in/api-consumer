package com.zp.apiconsumer.loadbalancer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServerStats {

    private int failedCalls;
    private int secondsThreashold;
}
