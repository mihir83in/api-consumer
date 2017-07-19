package com.zp.apiconsumer.acceptance.Model;

import com.zp.apiconsumer.commons.model.api.CurrencyList;
import com.zp.apiconsumer.commons.model.api.CurrencyRates;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;


@Getter
@Setter
@Component
public class ApiDataModel {
    private CurrencyRates currencyRates;
    private CurrencyList currencyList;
}
