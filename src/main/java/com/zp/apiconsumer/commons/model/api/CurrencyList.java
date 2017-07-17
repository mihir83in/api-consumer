package com.zp.apiconsumer.commons.model.api;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.collect.Lists;
import com.zp.apiconsumer.commons.deserializer.CurrencyListDeserializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize(using = CurrencyListDeserializer.class)
@ToString
public class CurrencyList {

    private List<CurrencyItem> currencies = Lists.newArrayList();

}
