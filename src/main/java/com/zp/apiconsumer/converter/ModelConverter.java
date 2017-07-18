package com.zp.apiconsumer.converter;

import com.zp.apiconsumer.commons.model.web.CurrencyConversionForm;
import com.zp.apiconsumer.commons.model.web.CurrencyQuery;
import com.zp.apiconsumer.commons.model.web.CurrencyUserRegistrationForm;
import com.zp.apiconsumer.persistence.model.CurrencyConvertQuery;
import com.zp.apiconsumer.persistence.model.CurrencyUser;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class ModelConverter {

    public CurrencyUser toCurrencyUser(CurrencyUserRegistrationForm form) {

        CurrencyUser currencyUser = new CurrencyUser();

        currencyUser.setAddress(form.getAddress());
        currencyUser.setBirthDate(form.getBirthDate());
        currencyUser.setCity(form.getCity());
        currencyUser.setCountry(form.getCountry());
        currencyUser.setEmail(form.getEmail());
        currencyUser.setFirstName(form.getFirstName());
        currencyUser.setLastName(form.getLastName());
        currencyUser.setPassword(form.getPassword());
        currencyUser.setUsername(form.getUsername());
        currencyUser.setZipCode(form.getZipCode());

        return currencyUser;

    }


    public CurrencyConvertQuery toCurrencyConvertQuery(CurrencyConversionForm form) {

        CurrencyConvertQuery query = new CurrencyConvertQuery();

        query.setAmount(form.getAmount());
        query.setDateEntered((Date) ObjectUtils.defaultIfNull(form.getDate(), new Date()));
        query.setFromCurrency(form.getFrom());
        query.setToCurrency(form.getTo());
        query.setCreated(new Date());

        return query;
    }


    public CurrencyQuery toCurrencyQuery(CurrencyConvertQuery query) {

        CurrencyQuery currencyQuery = new CurrencyQuery();
        currencyQuery.setAmount(query.getAmount());
        currencyQuery.setDate(query.getDateEntered());
        currencyQuery.setFrom(query.getFromCurrency());
        currencyQuery.setTo(query.getToCurrency());
        currencyQuery.setResult(query.getResult());
        currencyQuery.setRate(query.getRate());

        return currencyQuery;
    }
}
