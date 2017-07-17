package com.zp.apiconsumer.commons.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Maps;
import com.zp.apiconsumer.commons.Currency;
import com.zp.apiconsumer.commons.model.api.CurrencyRates;
import com.zp.apiconsumer.commons.model.api.ErrorResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;


public class CurrencyRatesDeserializer extends JsonDeserializer<CurrencyRates> {

    @Override
    public CurrencyRates deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        Map<Currency, BigDecimal> ratesMap = Maps.newHashMap();
        JsonNode rates = node.get("rates");

        if (rates == null || !rates.isObject()) {
            rates = node.get("quotes");
        }

        if (rates == null) {
            JsonNode error = node.get("error");
            int code = error.get("code").asInt();
            String msg = error.get("info").asText();
            return new CurrencyRates(Maps.newHashMap(), new ErrorResponse(code, msg));
        }

        Iterator<Map.Entry<String, JsonNode>> fields = rates.fields();

        while (fields.hasNext()) {

            Map.Entry<String, JsonNode> entry = fields.next();

            String key = entry.getKey();
            key = key.length() > 3 ? key.substring(3) : key;

            Currency currency = Currency.fromName(key);
            if (currency != null) {
                ratesMap.put(currency, entry.getValue().decimalValue());
            }
        }

        return new CurrencyRates(ratesMap, null);
    }
}
