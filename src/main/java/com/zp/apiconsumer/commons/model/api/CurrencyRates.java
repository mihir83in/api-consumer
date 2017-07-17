package com.zp.apiconsumer.commons.model.api;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.zp.apiconsumer.commons.Currency;
import com.zp.apiconsumer.commons.deserializer.CurrencyRatesDeserializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Map;


@Getter
@Setter
@JsonDeserialize(using = CurrencyRatesDeserializer.class)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CurrencyRates {

    private Map<Currency, BigDecimal> data;
    private ErrorResponse errorResponse;
}
