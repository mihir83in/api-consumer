package com.zp.apiconsumer.loadbalancer;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class ServerStats {

    private int failedCalls;
    private int secondsThreashold;
}
