package com.zp.apiconsumer.commons.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Lists;
import com.zp.apiconsumer.commons.model.api.CurrencyItem;
import com.zp.apiconsumer.commons.model.api.CurrencyList;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CurrencyListDeserializer extends JsonDeserializer<CurrencyList> {

    @Override
    public CurrencyList deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {

        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        List<CurrencyItem> list = Lists.newArrayList();

        JsonNode currencies = node.get("currencies");
        if (currencies == null || !currencies.isObject()) {
            currencies = node;
        }

        Iterator<Map.Entry<String, JsonNode>> fields = currencies.fields();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> entry = fields.next();
            list.add(new CurrencyItem(entry.getKey(), entry.getValue().asText()));
        }

        return new CurrencyList(list);
    }
}
