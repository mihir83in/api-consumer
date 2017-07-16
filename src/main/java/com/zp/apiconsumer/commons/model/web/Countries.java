package com.zp.apiconsumer.commons.model.web;

import com.google.common.collect.Maps;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

public class Countries {

    private static final Countries INSTANCE = new Countries();
    private Map<String, String> countriesMap = Maps.newLinkedHashMap();

    private Countries() {

        countriesMap = Arrays.stream(Locale.getISOCountries())
                .map(country -> new Locale(Locale.getDefault().getLanguage(), country))
                .sorted(Comparator.comparing(Locale::getDisplayCountry))
                .collect(Collectors.toMap(Locale::getDisplayCountry, Locale::getCountry, (v1, v2) -> v1,
                        LinkedHashMap::new));
    }

    public static Countries getInstance() {
        return INSTANCE;
    }

    public boolean isValid(String country) {
        return !StringUtils.isEmpty(country) && Optional.ofNullable(countriesMap.get(country)).isPresent();
    }

    public Collection<String> values() {
        return countriesMap.keySet();
    }
}
